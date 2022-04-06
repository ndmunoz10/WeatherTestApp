package com.nicdamun.weatherapp.features.locationDetail

import com.nicdamun.common.models.LocationDetailModel

sealed class LocationDetailViewState {
    object Loading : LocationDetailViewState()
    data class OnLocationDetailRetrieved(val locationDetail: LocationDetailModel) : LocationDetailViewState()
    object OnLocationDetailFailed : LocationDetailViewState()
}
