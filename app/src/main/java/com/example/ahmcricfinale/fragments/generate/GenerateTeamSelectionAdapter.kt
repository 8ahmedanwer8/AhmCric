package com.example.ahmcricfinale.fragments.generate

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmcricfinale.R
import kotlinx.android.synthetic.main.row_item_list.view.*


class GenerateTeamSelectionAdapter(): RecyclerView.Adapter<GenerateTeamSelectionAdapter.MyViewHolder>() {
    private var selectedUserList = GenerateTeamAdapter.selectedUserList

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_generate, parent, false))
    }

    override fun getItemCount() = selectedUserList.size //Kotlin single expression syntax

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.firstName_txt.text = selectedUserList[position].firstName

    }


//    fun setData(user: List<User>){
//        this.selectedUserList = user
//        notifyDataSetChanged()
//    }

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