package com.confuseddevs.ahmcricfinale.fragments.share

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.confuseddevs.ahmcricfinale.R
import kotlinx.android.synthetic.main.row_item_list.view.*

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
