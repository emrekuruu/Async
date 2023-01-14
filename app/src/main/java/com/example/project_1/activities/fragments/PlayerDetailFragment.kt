package com.example.project_1.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.project_1.databinding.FragmentPlayerDetailBinding


class PlayerDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlayerDetailBinding

    val args : PlayerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var viewModel = ViewModelProvider(this).get(com.example.project_1.activities.ViewModels.PlayerDetailViewModel::class.java)
        binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)

        var player = viewModel.getPlayer(args.playerName,context)
        binding.player =player

        return binding.root
    }

}
