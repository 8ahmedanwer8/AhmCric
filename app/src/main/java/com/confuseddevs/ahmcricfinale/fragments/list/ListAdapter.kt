package com.confuseddevs.ahmcricfinale.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.confuseddevs.ahmcricfinale.R
import com.confuseddevs.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.row_item_list.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    companion object{
        var userList = emptyList<User>()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_list, parent, false))
    }

    override fun getItemCount(): Int {
        userList = userList.sortedByDescending{
            var num = (it.wins.toFloat()/it.losses.toFloat())
            if(!(num.isInfinite() || num.isNaN())){
                it.wins.toFloat()/it.losses.toFloat()
            }
            else{
                it.wins.toFloat()
            }
        }
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

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
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        userList = user
        notifyDataSetChanged()
    }
}