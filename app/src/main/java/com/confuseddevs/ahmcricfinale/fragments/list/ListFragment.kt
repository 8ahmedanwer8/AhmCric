package com.confuseddevs.ahmcricfinale.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.confuseddevs.ahmcricfinale.R
import com.confuseddevs.ahmcricfinale.fragments.list.ListAdapter.Companion.userList
import com.confuseddevs.ahmcricfinale.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and set listSize if > 1
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.textView2.text = if (userList.size > 1) "(All ${userList.size} of them)" else " "


        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerViewPlayerTable
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        addOnBackPressedCallback {
            findNavController().navigateUp()
            Log.i("ListFragment", "Goin' back")
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_menu){
            deleteAllUsers()
            return true
        }
        if(item.itemId == android.R.id.home){
            findNavController().navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            builder.setPositiveButton("Yes"){_,_-> //ask one more time if user wants to delete everything
                builder.setTitle("Delete everything?")
                builder.setMessage("Are you sure you want to delete everything?")
                mUserViewModel.deleteAllUsers()
                Toast.makeText(
                        requireContext(),
                        "Successfully removed everything",
                        Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete everything?")
            builder.setMessage("Are you REALLY sure you want to delete ALL your player data?")
            builder.create().show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    //Back button on this fragment is overridden to take user to the home fragment
    private fun addOnBackPressedCallback(callback: () -> Unit) {
        activity?.onBackPressedDispatcher?.addCallback( viewLifecycleOwner, object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                callback()
                remove() }
        })
    }

}

