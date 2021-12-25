package com.example.ahmcricfinale.fragments.generate

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.generate.GenerateTeamAdapter.Companion.userList
import com.example.ahmcricfinale.model.User

import com.example.ahmcricfinale.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_generate_team.*
import kotlinx.android.synthetic.main.fragment_generate_team.view.*
import kotlinx.android.synthetic.main.fragment_list.view.recyclerViewPlayerTable


class GenerateTeamFragment : Fragment(),GenerateTeamAdapter.OnItemClickListener {

    companion object{//allows to make selection rv available to leaderboard rv
        val generateTeamSelectionAdapter = GenerateTeamSelectionAdapter()
    }
    var teamANames = mutableListOf<String>()
    var teamBNames = mutableListOf<String>()
    private var selectedUserList = GenerateTeamAdapter.selectedUserList
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_generate_team, container, false)
        initDisplay(view)
        // Recyclerview for leaderboard
        val generateTeamAdapter = GenerateTeamAdapter(this)
        val recyclerView = view.recyclerViewPlayerTable
        recyclerView.adapter = generateTeamAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true) //apparently optimizes the rv if size is fixed

        // Recyclerview for selected players
        val rv2 = view.recyclerViewGenerateTeams
        rv2.adapter = generateTeamSelectionAdapter
        rv2.layoutManager = LinearLayoutManager(requireContext())
        rv2.setHasFixedSize(true)

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            generateTeamAdapter.setData(user)
        })

        //Delete all selected players
        view.deleteBtn.setOnClickListener {
            reset()
        }

        //Add all players in the list
        view.addAllBtn.setOnClickListener {
            userList.forEach {
                addToRecyclerViewGenerate(it)
            }
            generateTeamSelectionAdapter.notifyDataSetChanged()
        }

        //Display the randomized team on a card view and handle some responsiveness
        view.generateBtn.setOnClickListener{
            if (selectedUserList.size > 22){
                Toast.makeText(requireContext(), "Cannot play cricket with more than 22 players total!", Toast.LENGTH_SHORT).show()
            }
            else{
                generateTeam(selectedUserList)

                if (teamANames.size < 4 && teamBNames.size <4){
                    teamAMembersTxt.setTextSize(20f)
                    teamBMembersTxt.setTextSize(20f)
                    generateCardView.teamAMembersTxt.text = teamANames.joinToString(separator = "   ")
                    generateCardView.teamBMembersTxt.text = teamBNames.joinToString(separator = "   ")
                }
                if (teamANames.size > 4 && teamBNames.size > 4){
                    teamAMembersTxt.setTextSize(18f)
                    teamBMembersTxt.setTextSize(18f)
                    generateCardView.teamAMembersTxt.text = teamANames.joinToString(separator = "   ")
                    generateCardView.teamBMembersTxt.text = teamBNames.joinToString(separator = "   ")
                }
                view.generateCardView.visibility = View.VISIBLE

                view.setOnClickListener {
                    view.generateCardView.visibility = View.GONE
                }
            }

        }
        return view
    }

    override fun onItemClick(position: Int) {
        addToRecyclerViewGenerate(userList[position])

        if (selectedUserList.isEmpty()){
            addAllBtn.visibility = View.GONE
            deleteBtn.visibility = View.GONE
            recyclerViewGenerateTeams.visibility = View.GONE
            generateBtn.visibility = View.GONE
        }
        if (selectedUserList.isNotEmpty()){
            addAllBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
            recyclerViewGenerateTeams.visibility = View.VISIBLE
            generateBtn.visibility = View.VISIBLE
        }
    }

    private fun addToRecyclerViewGenerate(user: User) {
        if (!GenerateTeamAdapter.selectedUserList.contains(user)) {
            GenerateTeamAdapter.selectedUserList.add(user)
        }
        generateTeamSelectionAdapter.notifyDataSetChanged()
        Log.i("GenerateTeamAdapter","selectedUserList {${GenerateTeamAdapter.selectedUserList}}")
    }

    //Hide everything except the generateRecyclerView
    private fun initDisplay(view: View){
        if (selectedUserList.isEmpty()){
            view.addAllBtn.visibility = View.GONE
            view.deleteBtn.visibility = View.GONE
            view.recyclerViewGenerateTeams.visibility = View.GONE
            view.generateBtn.visibility = View.GONE
        }
        if (selectedUserList.isNotEmpty()){
            view.addAllBtn.visibility = View.VISIBLE
            view.deleteBtn.visibility = View.VISIBLE
            view.recyclerViewGenerateTeams.visibility = View.VISIBLE
            view.generateBtn.visibility = View.VISIBLE
        }
    }

    private fun reset(){
        teamANames.clear()
        teamBNames.clear()
        selectedUserList.clear()
        generateTeamSelectionAdapter.notifyDataSetChanged()
        view?.deleteBtn?.visibility = View.GONE
        view?.recyclerViewGenerateTeams?.visibility = View.GONE
        view?.generateBtn?.visibility = View.GONE
        view?.addAllBtn?.visibility = View.GONE
    }

    //Take the selected players and randomly put them evenly on two teams
    private fun generateTeam(list: MutableList<User>){
        teamANames.clear() //Clear names so that they don't cascade when user spams generate button
        teamBNames.clear()
        var tempSelectedUserList = list.toMutableList()
        var teamA = mutableListOf<User>();
        var teamB = mutableListOf<User>();

        do{
            var tempPlayer: User
            if (tempSelectedUserList.isNotEmpty()){
                tempPlayer = tempSelectedUserList.random()
                teamA.add(tempPlayer)
                tempSelectedUserList.remove(tempPlayer)
            }

            if (tempSelectedUserList.isNotEmpty()){
                tempPlayer = tempSelectedUserList.random()
                teamB.add(tempPlayer)
                tempSelectedUserList.remove(tempPlayer)
            }
            else {
                break
            }
        }while(tempSelectedUserList.isNotEmpty())

        teamA.forEach{teamANames.add(it.firstName)}
        teamB.forEach{teamBNames.add(it.firstName)}
    }


    //Clears the selected players display when the fragment is exited out of
    override fun onPause() {
        super.onPause()
        selectedUserList.clear()
    }
}


