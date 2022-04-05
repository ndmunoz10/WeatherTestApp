package com.nicdamun.weatherapp.features.home

import com.nicdamun.common.models.LocationModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class OnLocationsLoaded(val locations: List<LocationModel>) : HomeViewState()
    object OnLocationsFailed : HomeViewState()
    object OnNoLocations : HomeViewState()
}
