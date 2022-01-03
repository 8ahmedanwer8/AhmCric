package com.confuseddevs.ahmcricfinale.fragments.share

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.confuseddevs.ahmcricfinale.R
import com.confuseddevs.ahmcricfinale.fragments.share.FoundSearchedTeamAdapter.Companion.foundUserSearchedPlayerList
import kotlinx.android.synthetic.main.fragment_share.view.recyclerViewPlayerTable
import kotlinx.android.synthetic.main.fragment_share.view.textView


class FoundPlayersRecyclerView : Fragment() {

    private val args by navArgs<FoundPlayersRecyclerViewArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and set title using safe args
        val view =  inflater.inflate(R.layout.fragment_found_players_recycler_view, container, false)
        view.textView.text = args.FoundPlayersRecyclerViewArgs

        //Initialize the reyclerview
        val adapter = FoundSearchedTeamAdapter()
        val foundRecyclerView = view.recyclerViewPlayerTable
        foundRecyclerView.adapter = adapter
        foundRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        view.textView
        return view
    }
    private fun reset(){
        foundUserSearchedPlayerList.clear()
    }
    override fun onPause() {
        super.onPause()
        reset()
    }

}