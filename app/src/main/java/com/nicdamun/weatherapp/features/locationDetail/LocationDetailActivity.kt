package com.nicdamun.weatherapp.features.locationDetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailActivity: AppCompatActivity() {

    companion object {
        const val LOCATION_WOEID = "LOCATION_WOEID_KEY"
    }

    private val viewModel by viewModels<LocationDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModelObservers()
        viewModel.getLocationDetails(getWoeidExtra())
    }

    private fun getWoeidExtra(): Int {
        return intent.getIntExtra(LOCATION_WOEID, 0)
    }

    private fun handleLoadingState() {

    }

    private fun handleOnLocationDetailRetrievedState() {

    }

    private fun handleOnLocationDetailFailed() {

    }

    private fun setViewModelObservers() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is LocationDetailViewState.Loading -> handleLoadingState()
                is LocationDetailViewState.OnLocationDetailRetrieved -> handleOnLocationDetailRetrievedState()
                is LocationDetailViewState.OnLocationDetailFailed -> handleOnLocationDetailFailed()
            }
        }
    }
}