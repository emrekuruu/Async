package com.example.project_1.activities.data

import android.content.Context
import com.example.project_1.activities.model.Player
import com.example.project_1.R

class PlayerDataSource(val context: Context) {

    fun getNames():Array<String>{
        return context.resources.getStringArray(R.array.name_array)
    }

    fun getPPG():Array<String>{
        return context.resources.getStringArray(R.array.ppg_array)
    }
    fun getAPG():Array<String>{
        return context.resources.getStringArray(R.array.apg_array)
    }
    fun getRPG():Array<String>{
        return context.resources.getStringArray(R.array.rpg_array)
    }

    fun getPhotoUrl():Array<String>{
        return context.resources.getStringArray(R.array.url_array)
    }

    suspend fun getPlayerByName(name : String) : Player? {
        val players = loadPlayers()
        for (player: Player in players) {
            if (player.name == name) {
                return player
            }
        }
        return null
    }

    suspend fun loadPlayers() : List<Player>{
        val players = mutableListOf<Player>()
        val nameList = getNames()
        val ppgs = getPPG()
        val apgs = getAPG()
        val rpgs = getRPG()
        val urls = getPhotoUrl()

        for(i in 0..14){
            val player = Player(nameList[i],ppgs[i],apgs[i],rpgs[i],urls[i])
            players.add(player)
        }
        return players
    }
}