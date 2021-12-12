package com.example.ahmcricfinale.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.generateTeamFragment.GenerateTeamSelectionAdapter
import com.example.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.row_item_list.view.*

//var userSelected: String? = null
val selectedUserList = mutableListOf<User>();
class GenerateTeamAdapter: RecyclerView.Adapter<GenerateTeamAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
//    private var selectedUserList = emptyList<User>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.wins_txt.text = currentItem.wins.toString()
        holder.itemView.losses_txt.text = currentItem.losses.toString()
        holder.itemView.draws_txt.text = currentItem.draws.toString()
        holder.itemView.matches_txt.text = (currentItem.wins+currentItem.losses+currentItem.draws).toString()
        holder.itemView.winLossRatio_txt.text = "%.2f".format((currentItem.wins.toFloat()/currentItem.losses.toFloat()).takeIf { !it.isNaN() } ?: 0.0)


        holder.itemView.rowLayout.setOnClickListener {
            addToRecyclerViewGenerate(userList[position])
//            Log.i("GenerateTeamAdapter","the position $position, the userlist ${userList[position]}, userSelected {$userSelected}")

//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
        }
    }

    private fun addToRecyclerViewGenerate(user: User) {
        if (!selectedUserList.contains(user)) {
            selectedUserList.add(user)
        }
        Log.i("GenerateTeamAdapter","selectedUserList {$selectedUserList}")
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}