package com.example.ahmcricfinale.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahmcricfinale.R

import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.homeBtnOne.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }
        view.homeBtnTwo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_generateTeamFragment)
        }
        view.homeBtnThree.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }

        view.homeBtnFour.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_shareFragment)
        }

        addOnBackPressedCallback {
            findNavController().navigateUp()
        }

        return view
    }
    private fun addOnBackPressedCallback(callback: () -> Unit) {
        activity?.onBackPressedDispatcher?.addCallback( viewLifecycleOwner, object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                callback()
                remove() }
        })
    }

}