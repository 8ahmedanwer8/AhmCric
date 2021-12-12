package com.example.ahmcricfinale.fragments.generateTeamFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.list.GenerateTeamAdapter
import com.example.ahmcricfinale.fragments.list.ListAdapter
import com.example.ahmcricfinale.fragments.list.selectedUserList
import com.example.ahmcricfinale.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_generate_team.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_list.view.recyclerViewTable


class generateTeamFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_generate_team, container, false)

        // Recyclerview
        val generateTeamAdapter = GenerateTeamAdapter()
        val recyclerView = view.recyclerViewTable
        recyclerView.adapter = generateTeamAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val recyclerGenerateTeamSelectionAdapter = GenerateTeamSelectionAdapter()
        val rv2 = view.recyclerViewGenerateTeams
        rv2.adapter = recyclerGenerateTeamSelectionAdapter
        rv2.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            generateTeamAdapter.setData(user)
        })

//        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { selectedUser ->
//            recyclerGenerateTeamSelectionAdapter.setData(selectedUser)
//        })

        view.deleteBtn.setOnClickListener {
            deleteAll()
        }
        return view
    }

    private fun deleteAll() {
//        val size: Int = selectedUserList.size
        selectedUserList.clear()
//        notifyItemRangeRemoved(0, size)
    }

}