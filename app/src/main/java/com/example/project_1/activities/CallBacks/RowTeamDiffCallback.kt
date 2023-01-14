package com.example.project_1.activities.CallBacks

import androidx.recyclerview.widget.DiffUtil
import com.example.project_1.activities.model.Team

class RowTeamDiffCallback : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return(oldItem.name == newItem.name)
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}