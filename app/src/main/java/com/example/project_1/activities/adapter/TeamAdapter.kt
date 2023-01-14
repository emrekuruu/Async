package com.example.project_1.activities.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.activities.CallBacks.RowTeamDiffCallback
import com.example.project_1.activities.model.Player
import com.example.project_1.activities.model.Team
import com.example.project_1.databinding.TeamViewBinding

class TeamAdapter(var clickListener: TeamClickListener) : ListAdapter<Team, TeamAdapter.TeamViewHolder>(RowTeamDiffCallback()) {

    class TeamViewHolder(val binding : TeamViewBinding ) :  RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): TeamViewHolder {
                val binding = TeamViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return TeamViewHolder(binding)
            }
        }

        lateinit var team : Team

        fun bind(team: Team,clickListener: TeamClickListener){
            binding.team = team
            binding.clickListener = clickListener
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team,clickListener)
    }

    class TeamClickListener(val clickListener: (teamName:String) -> Unit){
        fun onClick(team : Team) = clickListener(team.name)
    }

}