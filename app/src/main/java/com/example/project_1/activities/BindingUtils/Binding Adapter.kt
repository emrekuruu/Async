package com.example.project_1.activities.BindingUtils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.project_1.R
import com.example.project_1.activities.model.Player
import com.example.project_1.activities.model.Team

@BindingAdapter("playerPhoto")
fun ImageView.setPlayerImage(player: Player) {
        Glide.with(this)
            .load(player.photoUrl)
            .into(this)
    }

@BindingAdapter("teamPhoto")
fun ImageView.setTeamImage(team : Team){
    Glide.with(this)
        .load(team.photoUrl)
        .into(this)
}

