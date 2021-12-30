package com.example.ahmcricfinale.fragments.generate

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmcricfinale.R
import com.example.ahmcricfinale.fragments.generate.GenerateTeamFragment.Companion.generateTeamSelectionAdapter
import com.example.ahmcricfinale.model.User
import kotlinx.android.synthetic.main.row_item_list.view.*


class GenerateTeamAdapter(private val listener: GenerateTeamFragment) : RecyclerView.Adapter<GenerateTeamAdapter.MyViewHolder>() {


    companion object {//allows to make selectionList publicly available to display in selection rv
        var selectedUserList = mutableListOf<User>();
        var userList = emptyList<User>()
    }

    //making below class an inner class allows it to access listener parameter
    //which is outside in the adapter class. the interface allows us to interface
    //with any fragment/activity and keep it decoupled from the this adapter.
    //keeps adapter modular and reusable
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){//safely passes position if it actually
                //exists. because you can click on an item when its deleted but still click
                //on it because animation or slow rendering.
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_list, parent, false))
    }

    override fun getItemCount():Int {
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
//        holder.itemView.rowLayout.setOnClickListener {
//            addToRecyclerViewGenerate(userList[position], holder.itemView)
//        }
    }
    fun addToRecyclerViewGenerate(user: User, background: View) {
        if (!selectedUserList.contains(user)) {
            selectedUserList.add(user)
            background.setBackgroundResource(R.color.green)
        }

        generateTeamSelectionAdapter.notifyDataSetChanged()
        Log.i("GenerateTeamAdapter","selectedUserList {$selectedUserList}")
    }

    fun setHighlighted(item:View){
        item.setBackgroundResource(R.color.green)
    }

    fun setData(user: List<User>){
        userList = user
        notifyDataSetChanged()
    }
}