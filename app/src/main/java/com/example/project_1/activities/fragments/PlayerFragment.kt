package com.example.project_1.activities.fragments

import android.os.Bundle
import android.text.Layout.Directions
import android.view.*
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
import com.example.project_1.activities.data.PlayerDataSource
import com.example.project_1.databinding.FragmentPlayersBinding
import com.example.project_1.activities.model.Player
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlayerFragment : Fragment() {

    private val _navigateToPlayerDetail = MutableLiveData<String?>()

    val navigateToPlayerDetail
    get() = _navigateToPlayerDetail

    fun onPlayerClicked(playerName : String){
        _navigateToPlayerDetail.value = playerName
    }

    fun doneNavigating(){
        _navigateToPlayerDetail.value = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentPlayersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false)

        // Initialize the RecyclerView
        val recyclerView: RecyclerView = binding.playersRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {


                val players = context?.let { PlayerDataSource(it).loadPlayers() }


                val playerAdapter = players?.let {
                    PlayerAdapter(PlayerAdapter.PlayerClickListener { playerName ->
                        Toast.makeText(context, "${playerName} is chosen", Toast.LENGTH_SHORT)
                            .show()
                        onPlayerClicked(playerName)
                    })
                }

                recyclerView.adapter = playerAdapter
                playerAdapter?.submitList(players)



                playerAdapter?.setOnCheckboxClickListener(object :
                    PlayerAdapter.OnCheckboxClickListener {
                    override fun onCheckboxClick(player: Player) {
                        val navView = activity?.findViewById<NavigationView>(R.id.fav_nav)
                        val menu = navView?.menu
                        if (menu != null) {
                            if (menu.findItem(player.name.hashCode()) == null) {
                                menu.add(Menu.NONE, player.name.hashCode(), Menu.NONE, player.name)
                                Toast.makeText(
                                    context,
                                    "${player.name} added to favorites",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                menu.removeItem(player.name.hashCode())
                                Toast.makeText(
                                    context,
                                    "${player.name} removed from favorites",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })

            }
        }
        navigateToPlayerDetail.observe(viewLifecycleOwner, Observer { players ->
            players?.let {
                this.findNavController().navigate(
                    PlayerFragmentDirections.actionPlayerFragmentToPlayerDetailFragment(it)
                )
                doneNavigating()
            }
        })
        return binding.root
    }
}

