package com.example.project_1.activities.CallBacks

import androidx.recyclerview.widget.DiffUtil
import com.example.project_1.activities.model.Player

class RowPlayerDiffCallback  : DiffUtil.ItemCallback<Player>(){
    override fun areItemsTheSame(oldPlayer : Player, newPlayer : Player):Boolean{
        return oldPlayer == newPlayer
    }

    override fun areContentsTheSame(oldPlayer: Player, newPlayer: Player): Boolean {
        val n : Boolean = ( oldPlayer.name ==  newPlayer.name )
        val p : Boolean =  ( oldPlayer.PPG == newPlayer.PPG )
        val a : Boolean =  ( oldPlayer.PPG == newPlayer.APG )
        val r : Boolean =  ( oldPlayer.PPG == newPlayer.RPG )
        return (n && p && a && r)
    }
}