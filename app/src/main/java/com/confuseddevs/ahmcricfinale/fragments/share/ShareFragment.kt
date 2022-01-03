package com.confuseddevs.ahmcricfinale.fragments.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.confuseddevs.ahmcricfinale.R
import com.confuseddevs.ahmcricfinale.fragments.share.FoundSearchedTeamAdapter.Companion.foundUserSearchedPlayerList
import com.confuseddevs.ahmcricfinale.fragments.share.ShareAdapter.Companion.foundPlayerList
import com.confuseddevs.ahmcricfinale.model.User
import com.confuseddevs.ahmcricfinale.viewmodel.UserViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_share.*
import kotlinx.android.synthetic.main.fragment_share.view.*
import kotlinx.android.synthetic.main.fragment_share.view.addBtn
import kotlinx.android.synthetic.main.fragment_share.view.recyclerViewPlayerTable


class ShareFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var database: DatabaseReference
    private var currentPlayerList = listOf<User>()
    val adapter = ShareAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_share, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        getAllPLayers() //Get all all players and their data

        //Initialize the reyclerview
        val foundRecyclerView = view.recyclerViewPlayerTable
        foundRecyclerView.adapter = adapter
        foundRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Fetch all teams around the world for this fragment's rv
        findAllPlayers()
        view.reloadBtn.setOnClickListener{
            findAllPlayers()
        }


        //Button listeners
        view.addBtn.setOnClickListener {
            view.addBtn.isEnabled = false //disable and re-enable buttons so user doesn't spam

            if (teamNameEt.text.isEmpty()){
                Toast.makeText(requireContext(), "Please enter the team name", Toast.LENGTH_SHORT).show()
            }
            else{
                var teamName = teamNameEt?.text.toString()
                database = FirebaseDatabase.getInstance().getReference("Users")

                database.child(teamName).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value != null) {
                            Toast.makeText(
                                requireContext(),
                                "A team already exists with that name!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            uploadPlayers(teamName)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            requireContext(),
                            "Failed to check if team name already exists. $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            }
        }

        view.findBtn.setOnClickListener {
            view.findBtn.isEnabled = false

            if (findTeamNameEt.text.isEmpty()){
                Toast.makeText(requireContext(), "Please enter the team name", Toast.LENGTH_SHORT).show()
            }
            else{
                var teamName = findTeamNameEt?.text.toString()

                database = FirebaseDatabase.getInstance().getReference("Users")

                database.child(teamName).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value == null) {
                            Toast.makeText(
                                requireContext(),
                                "That team does not exist!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            findPlayers(teamName)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            requireContext(),
                            "Failed to check if team name already exists. $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            }
        }
        return view
    }

    //Functions

    private fun findAllPlayers(){
        foundPlayerList.clear()
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (items in snapshot.children) {
                    foundPlayerList.add((items.key).toString())
                }
                Toast.makeText(requireContext(), "Successfully loaded all team names around the world", Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to retrieve all teams around the world. $error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun findPlayers(teamName: String){
        database.child(teamName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foundUserSearchedPlayerList.clear()
                for (items in snapshot.children) {
                    var id: Int = (items.child("id").value as Long).toInt()
                    var firstName = items.child("firstName").value.toString()
                    var losses = (items.child("losses").value as Long).toInt()
                    var draws = (items.child("draws").value as Long).toInt()
                    var wins = (items.child("wins").value as Long).toInt()
                    var user: User = User(id, firstName, wins, losses, draws)
                    foundUserSearchedPlayerList.add(user)
                }
                val action = ShareFragmentDirections.actionShareFragmentToFoundPlayersRecyclerView(teamName)
                findNavController().navigate(action)
                Toast.makeText(requireContext(), "Found $teamName!", Toast.LENGTH_SHORT).show()
                view?.findBtn?.isEnabled = true
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to find $teamName. $error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun uploadPlayers(teamName: String){
        database.child(teamName).setValue(currentPlayerList).addOnSuccessListener {
            Toast.makeText(requireContext(), "Successfully uploaded your team!", Toast.LENGTH_SHORT).show()
            view?.addBtn?.isEnabled = true
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to upload. $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllPLayers(){
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            currentPlayerList = user
        })
    }

    override fun onPause() {
        super.onPause()
    }

}
