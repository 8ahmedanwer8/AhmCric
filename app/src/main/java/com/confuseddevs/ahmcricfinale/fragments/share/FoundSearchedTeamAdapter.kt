package com.confuseddevs.ahmcricfinale.fragments.share

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.confuseddevs.ahmcricfinale.R
import com.confuseddevs.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.row_item_list.view.*

class FoundSearchedTeamAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        var foundUserSearchedPlayerList = mutableListOf<User>()
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = foundUserSearchedPlayerList[position]
        holder.itemView.globalTeamName.text = currentItem.firstName
        holder.itemView.wins_txt.text = currentItem.wins.toString()
        holder.itemView.losses_txt.text = currentItem.losses.toString()
        holder.itemView.draws_txt.text = currentItem.draws.toString()
        holder.itemView.matches_txt.text = (currentItem.wins+currentItem.losses+currentItem.draws).toString()
        val wr = (currentItem.wins.toFloat()/currentItem.losses.toFloat())
        when {
            wr.isNaN() -> {holder.itemView.winLossRatio_txt.text = "0.00"}
            wr.isInfinite() -> {holder.itemView.winLossRatio_txt.text = "%.2f".format(currentItem.wins.toFloat())}
            else -> {holder.itemView.winLossRatio_txt.text = "%.2f".format(wr)}
        }
    }

    override fun getItemCount(): Int {
        Log.i("f", "$foundUserSearchedPlayerList")
        foundUserSearchedPlayerList.sortByDescending{
            var num = (it.wins.toFloat()/it.losses.toFloat())
            if(!(num.isInfinite() || num.isNaN())){
                it.wins.toFloat()/it.losses.toFloat()
            }
            else{
                it.wins.toFloat()
            }
        }
        return foundUserSearchedPlayerList.size
    }

}
