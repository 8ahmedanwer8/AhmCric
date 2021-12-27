package com.example.ahmcricfinale.fragments.list

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
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.game.GameFragment
import com.example.ahmcricfinale.fragments.list.ListAdapter.Companion.userList
import com.example.ahmcricfinale.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and set listSize if > 1
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.listSize.text = if (userList.size > 1) "(All ${userList.size} of them)" else " "


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

        view.addPlayerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        addOnBackPressedCallback {
            Log.i("fuc", "fuck")
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
            Log.i("ListFragment", "Clicked the back button")
            findNavController().navigate(R.id.action_listFragment_to_homeFragment)
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
                findNavController().navigate(R.id.action_listFragment_to_homeFragment)
                callback()
                remove() }
        })
    }

}

