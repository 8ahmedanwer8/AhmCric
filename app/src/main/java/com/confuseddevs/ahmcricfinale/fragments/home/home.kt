package com.confuseddevs.ahmcricfinale.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.confuseddevs.ahmcricfinale.R

import kotlinx.android.synthetic.main.fragment_home.view.*

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

        return view
    }

}