package com.example.project_1.activities.data

import android.content.Context
import com.example.project_1.activities.model.Team
import com.example.project_1.R

class TeamDataSource(val context: Context) {

    fun getNames():Array<String>{
        return context.resources.getStringArray(R.array.team_array)
    }

    fun getRecord():Array<String>{
        return context.resources.getStringArray(R.array.record_array)
    }

    fun getUrls():Array<String>{
        return context.resources.getStringArray(R.array.team_urls)
    }

    suspend fun getTeamByName(name : String) : Team?{
        val teams = loadTeams()
        for(team : Team in teams){
            if(team.name == name){
                return team
            }
        }
        return null
    }

    suspend fun loadTeams():List<Team>{
        val teams = mutableListOf<Team>()
        val names = getNames()
        val records = getRecord()
        val urls = getUrls()

        for(i in 0..5){
            val team = Team(names[i],records[i],urls[i])
            teams.add(team)
        }
        return teams
    }
}