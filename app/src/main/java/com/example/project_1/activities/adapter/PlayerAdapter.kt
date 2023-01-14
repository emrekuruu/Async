package com.example.project_1.activities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.activities.model.Player
import com.example.project_1.R
import com.example.project_1.activities.CallBacks.RowPlayerDiffCallback
import com.example.project_1.databinding.PlayerViewBinding

class PlayerAdapter(var clickListener: PlayerClickListener) : ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(RowPlayerDiffCallback()) {


    interface OnCheckboxClickListener {
        fun onCheckboxClick(player: Player)
    }
    private var listener: OnCheckboxClickListener? = null
    fun setOnCheckboxClickListener(listener: OnCheckboxClickListener?) {
        this.listener = listener
    }

    class PlayerViewHolder(val binding : PlayerViewBinding) :  RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup): PlayerViewHolder {
                val binding = PlayerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return PlayerViewHolder(binding)
            }
        }

        lateinit var player : Player


        fun bind(player: Player,clickListener: PlayerClickListener){
            binding.player = player
            binding.clickListener = clickListener
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)
        holder.itemView.findViewById<CheckBox>(R.id.player_checkbox).setOnCheckedChangeListener { _, isChecked ->
                listener?.onCheckboxClick(player)
        }
        holder.bind(player,clickListener)
    }

    class PlayerClickListener(val clickListener: (playerName : String) -> Unit){
        fun onClick(player : Player) = clickListener(player.name)
    }

}
