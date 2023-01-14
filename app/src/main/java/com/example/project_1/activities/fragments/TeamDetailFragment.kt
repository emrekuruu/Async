package com.example.project_1.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.project_1.databinding.FragmentTeamDetailBinding

class TeamDetailFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailBinding

    val args : TeamDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var viewModel = ViewModelProvider(this).get(com.example.project_1.activities.ViewModels.TeamDetailViewModel::class.java)

        binding = FragmentTeamDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        binding.team = viewModel.getTeam(args.teamName,context)

        return binding.root
    }
}
