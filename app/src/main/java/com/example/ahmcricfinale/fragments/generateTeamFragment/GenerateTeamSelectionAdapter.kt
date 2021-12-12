package com.example.ahmcricfinale.fragments.generateTeamFragment

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.list.selectedUserList
import com.example.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.fragment_generate_team.view.*
import kotlinx.android.synthetic.main.row_item_list.view.*


class GenerateTeamSelectionAdapter: RecyclerView.Adapter<GenerateTeamSelectionAdapter.MyViewHolder>() {

//    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_generate, parent, false))
    }

    override fun getItemCount(): Int {
        return selectedUserList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.firstName_txt.text = selectedUserList[position].firstName
    }

//    fun setData(selectedUser: List<String>){
//        selectedUserList = selectedUser
//        notifyDataSetChanged()
//    }

//    fun deleteItem(index: Int){
//        forEach
//        selectedUserList.removeAt(index)
//        notifyDataSetChanged()
//    }
//    fun deleteAll() {
//        val size: Int = selectedUserList.size
//        selectedUserList.clear()
//        notifyItemRangeRemoved(0, size)
//    }

}