package com.example.project_1.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.activities.adapter.PlayerAdapter
import com.example.project_1.activities.adapter.TeamAdapter
import com.example.project_1.activities.data.PlayerDataSource
import com.example.project_1.activities.data.TeamDataSource
import com.example.project_1.activities.model.Team
import com.example.project_1.databinding.FragmentPlayersBinding
import com.example.project_1.databinding.FragmentTeamsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamFragment : Fragment() {

    private val _navigateToTeamDetail = MutableLiveData<String?>()

    val navigateToTeamDetail
    get() = _navigateToTeamDetail


    fun onTeamClicked(teamName : String){
        _navigateToTeamDetail.value = teamName
    }

    fun doneNavigating(){
        _navigateToTeamDetail.value = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentTeamsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_teams, container, false)

        // Initialize the RecyclerView
        val recyclerView: RecyclerView = binding.teamsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch {
            withContext(Dispatchers.IO){
                val teams = context?.let { TeamDataSource(it).loadTeams() }
                val teamAdapter = teams?.let { TeamAdapter(TeamAdapter.TeamClickListener { teamName ->
                    Toast.makeText(context?.applicationContext, "${teamName} is chosen", Toast.LENGTH_SHORT).show()
                    onTeamClicked(teamName)
                }) }

                recyclerView.adapter = teamAdapter
                teamAdapter?.submitList(teams)

            }
        }

        navigateToTeamDetail.observe(viewLifecycleOwner, Observer { teams -> teams?.let {
            this.findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToTeamDetailFragment(it))
            doneNavigating()
        } })


        // Return the inflated layout
        return binding.root
    }

}

