package com.nicdamun.weatherapp.features.home

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.nicdamun.common.models.LocationModel
import com.nicdamun.weatherapp.R
import com.nicdamun.weatherapp.databinding.ActivityHomeBinding
import com.nicdamun.weatherapp.extensions.doAfterTextChangedDelayed
import com.nicdamun.weatherapp.features.locationDetail.LocationDetailActivity
import com.nicdamun.weatherapp.utils.PermissionsUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: LocationAdapter
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewListeners()
        setViewModelObservers()
        initLocationList()
        requestForLocationPermission()
    }

    private fun handleInitialState() {
        with(binding) {
            groupNothingFound.isVisible = true
            pbLoading.isVisible = false
            rvLocationList.isVisible = false
            tvNothingFound.text = getString(R.string.initial_state_description)
        }
    }

    private fun handleLoadingState() {
        with(binding) {
            groupNothingFound.isVisible = false
            pbLoading.isVisible = true
            rvLocationList.isVisible = false
        }
    }

    private fun handleNothingFoundState() {
        with(binding) {
            groupNothingFound.isVisible = true
            pbLoading.isVisible = false
            rvLocationList.isVisible = false
            tvNothingFound.text = getString(R.string.nothing_found_here)
        }
    }

    private fun handleOnLocationsFoundState(locations: List<LocationModel>) {
        with(binding) {
            groupNothingFound.isVisible = false
            pbLoading.isVisible = false
            rvLocationList.isVisible = true
        }
        adapter.submitList(locations)
    }

    private fun initLocationList() {
        adapter = LocationAdapter(::navigateToLocationDetailsActivity)
        binding.rvLocationList.adapter = adapter
    }

    private fun navigateToLocationDetailsActivity(location: LocationModel) {
        val intent = Intent(this, LocationDetailActivity::class.java).apply {
            putExtra(LocationDetailActivity.LOCATION_WOEID, location.woeid)
        }
        startActivity(intent)
    }

    private fun requestForLocationPermission() {
        lifecycleScope.launchWhenCreated {
            // Using this scope since we need to request for permissions
            // everytime the activity recreates after config changes
            PermissionsUtils.requestForPermission(
                this@HomeActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    private fun setViewListeners() {
        with(binding) {
            tieSearchBar.doAfterTextChangedDelayed {
                viewModel.getLocationsByName(tieSearchBar.text.toString())
            }
        }
    }

    private fun setViewModelObservers() {
        viewModel.state.observe(this) { state: HomeViewState ->
            when (state) {
                is HomeViewState.InitialState -> handleInitialState()
                is HomeViewState.Loading -> handleLoadingState()
                is HomeViewState.OnLocationsFailed -> handleNothingFoundState()
                is HomeViewState.OnLocationsLoaded -> handleOnLocationsFoundState(state.locations)
                is HomeViewState.OnNoLocations -> handleNothingFoundState()
            }
        }
    }
}