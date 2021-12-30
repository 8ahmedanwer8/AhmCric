package com.example.ahmcricfinale.fragments.share

import android.util.Log
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.game.GameAdapter
import com.example.ahmcricfinale.fragments.list.ListFragmentDirections
import com.example.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.row_item_list.view.*
import kotlin.collections.sortByDescending

class ShareAdapter: RecyclerView.Adapter<ShareAdapter.MyViewHolder>() {
    companion object{
        var foundPlayerList = mutableListOf<String>()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_name_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foundPlayerList[position]
        holder.itemView.rowLayout.globalTeamName.text = currentItem
    }

    override fun getItemCount(): Int {
        foundPlayerList.sortBy{it}
        return foundPlayerList.size
    }


}
