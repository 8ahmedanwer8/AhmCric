package com.example.ahmcricfinale.fragments.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.game.GameAdapter.Companion.userList
import com.example.ahmcricfinale.fragments.update.UpdateFragmentArgs
import com.example.ahmcricfinale.model.User
import com.example.ahmcricfinale.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.teamAMembersTxt
import kotlinx.android.synthetic.main.fragment_game.teamBMembersTxt
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_game.view.deleteBtn


import kotlinx.android.synthetic.main.fragment_list.view.recyclerViewPlayerTable


class GameFragment : Fragment(),GameAdapter.OnItemClickListener {

    private var playersUserList = mutableListOf<User>() //list of clicked players
    private var teamAPlayers= mutableListOf<User>() //clicked players added to team A
    private var teamBPlayers = mutableListOf<User>() //clicked players added to team B
    var teamAPlayersNames = mutableListOf<String>() //team A players names strings
    var teamBPlayersNames = mutableListOf<String>() //team B players names strings
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_game, container, false)
        val addToTeamABtn = view.addToTeamABtn
        val addToTeamBBtn = view.addToTeamBBtn
        val deleteBtn = view.deleteBtn


        val gameAdapter = GameAdapter(this)
        val recyclerView = view.recyclerViewPlayerTable
        recyclerView.adapter = gameAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true) //apparently optimizes the rv if size is fixed

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            gameAdapter.setData(user)
        })

        addToTeamABtn.setOnClickListener {
            playersUserList.forEach {
                if(!((teamAPlayers).contains(it)) && !((teamBPlayers).contains(it))){
                    teamAPlayers.add(it)
                    teamAPlayersNames.add(it.firstName)
                }
            }

            if (teamAPlayers.isNotEmpty() && teamAPlayers.size <=11){
                teamAListTxt.text = teamAPlayersNames.toString()
                teamAListTxt.text  = teamAPlayersNames.joinToString(separator = "\n")
            }
            if (teamAPlayers.size ==11){
                Toast.makeText(requireContext(), "Max limit of 11 players in Team A reached", Toast.LENGTH_SHORT).show()
            }
            playersUserList.clear()
        }

        deleteBtn.setOnClickListener{
            reset()
        }

        addToTeamBBtn.setOnClickListener {
            playersUserList.forEach {
                if(!((teamBPlayers).contains(it)) && !((teamAPlayers).contains(it))){
                    teamBPlayers.add(it)
                    teamBPlayersNames.add(it.firstName)

                }
            }

            if (teamBPlayers.isNotEmpty() && teamBPlayers.size <=11){

                teamBListTxt.text = teamBPlayersNames.toString()
                teamBListTxt.text  = teamBPlayersNames.joinToString(separator = "\n")
            }
            if (teamBPlayers.size == 11){
                Toast.makeText(requireContext(), "Max limit of 11 players in Team B reached", Toast.LENGTH_SHORT).show()
            }
            playersUserList.clear()
        }

        view.startBtn.setOnClickListener{
            Log.i("nig", "$teamAPlayersNames,$teamBPlayersNames, $teamAPlayers,$teamBPlayers")
            if (teamAPlayers.isNotEmpty() && teamBPlayers.isNotEmpty()){
                if (teamAPlayersNames.size <= 4 || teamBPlayersNames.size <= 4){
                    teamAMembersTxt.setTextSize(20f)
                    teamBMembersTxt.setTextSize(20f)
                    startGameCardView.teamAMembersTxt.text = teamAPlayersNames.joinToString(separator = "   ")
                    startGameCardView.teamBMembersTxt.text = teamBPlayersNames.joinToString(separator = "   ")
                }
                if (teamAPlayersNames.size > 4 || teamBPlayersNames.size > 4){
                    teamAMembersTxt.setTextSize(18f)
                    teamBMembersTxt.setTextSize(18f)
                    startGameCardView.teamAMembersTxt.text = teamAPlayersNames.joinToString(separator = "   ")
                    startGameCardView.teamBMembersTxt.text = teamBPlayersNames.joinToString(separator = "   ")
                }
                view.startGameCardView.visibility = View.VISIBLE
                view.setOnClickListener {
                    view.startGameCardView.visibility = View.GONE
                }
            }
            else{
                Toast.makeText(requireContext(), "One of the teams are empty", Toast.LENGTH_SHORT).show()
            }
        }

        view.suspendBtn.setOnClickListener{
            reset()
        }

        //Hide finish and suspend buttons and show buttons to select game result
        view.finishBtn.setOnClickListener{
            drawBtn.visibility = View.VISIBLE
            teamAWonBtn.visibility = View.VISIBLE
            teamBWonBtn.visibility = View.VISIBLE
            finishBtn.visibility = View.GONE
            suspendBtn.visibility = View.GONE
        }

        //Hide start game cardview and show congrats cardview with appropriate team name
        view.teamAWonBtn.setOnClickListener{
            updateUser(1)
            startGameCardView.visibility = View.GONE
            congratsGameCardView.visibility = View.VISIBLE
            congratsTxt.text = "Congrats Team A!"

            var winnersTxtArray = mutableListOf<String>()
            teamAPlayers.forEach {
                winnersTxtArray.add("${it.firstName} now has ${it.wins+1} many wins!")
            }
            winnersTxt.text = winnersTxtArray.joinToString(separator = "\n")
        }

        view.teamBWonBtn.setOnClickListener {
            updateUser(2)
            startGameCardView.visibility = View.GONE
            congratsGameCardView.visibility = View.VISIBLE
            congratsTxt.text = "Congrats Team B!"

            var winnersTxtArray = mutableListOf<String>()
            teamBPlayers.forEach {
                winnersTxtArray.add("${it.firstName} now has ${it.wins+1} many wins!")
            }
            winnersTxt.text = winnersTxtArray.joinToString(separator = "\n")
        }

        view.drawBtn.setOnClickListener {
            updateUser(0)
            startGameCardView.visibility = View.GONE
            congratsGameCardView.visibility = View.GONE
            reset()
            Toast.makeText(requireContext(), "Game was drawn...", Toast.LENGTH_SHORT).show()
        }

        //Close and reset everything when game has finished
        view.closeBtn.setOnClickListener {
            startGameCardView.visibility = View.GONE
            congratsGameCardView.visibility = View.GONE
            reset()
        }
        return view
    }

    private fun updateUser(task: Int) {
        if (task == 0){
            teamAPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins,it.losses,it.draws+1)
                mUserViewModel.updateUser(updatedUser)
            }
            teamBPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins,it.losses,it.draws+1)
                mUserViewModel.updateUser(updatedUser)
            }
        }
        if (task == 1){
            teamAPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins+1,it.losses,it.draws)
                mUserViewModel.updateUser(updatedUser)
            }
            teamBPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins,it.losses+1,it.draws)
                mUserViewModel.updateUser(updatedUser)
            }
        }
        if (task == 2){
            teamAPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins,it.losses+1,it.draws)
                mUserViewModel.updateUser(updatedUser)
                Log.i("5", "$updatedUser")
            }
            teamBPlayers.forEach {
                val updatedUser = User(it.id, it.firstName,it.wins+1,it.losses,it.draws)
                mUserViewModel.updateUser(updatedUser)
            }
        }
    }

    override fun onItemClick(position: Int) {
        if (!playersUserList.contains(userList[position])){playersUserList.add(userList[position])}
    }

    private fun reset(){
        teamAPlayersNames.clear()
        teamBPlayersNames.clear()
        teamAPlayers.clear()
        teamBPlayers.clear()
        playersUserList.clear()
        teamAListTxt.text = ""
        teamBListTxt.text = ""
        startGameCardView.visibility = View.GONE
    }



}