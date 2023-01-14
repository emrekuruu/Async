package com.example.project_1.activities.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.activities.data.PlayerDataSource
import com.example.project_1.activities.model.Player
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PlayerDetailViewModel : ViewModel()  {

    fun getPlayer(playerName: String, context: Context?) : Player? {
        var result : Player? = null
        viewModelScope.launch {
        val db = context?.let { PlayerDataSource(it) }
        if (db != null) {
             result = db.getPlayerByName(playerName)
        }
    }
        return result
    }

}