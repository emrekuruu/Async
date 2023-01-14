package com.example.project_1.activities.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.activities.data.TeamDataSource
import com.example.project_1.activities.model.Team
import kotlinx.coroutines.launch

class TeamDetailViewModel : ViewModel() {


    fun getTeam(teamName: String, context: Context?) : Team? {
        var result : Team? = null
        viewModelScope.launch {
            val db = context?.let { TeamDataSource(it) }
            if (db != null) {
                result =  db.getTeamByName(teamName)
            }
        }
        return result
    }

}