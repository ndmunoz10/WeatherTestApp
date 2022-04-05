package com.nicdamun.weatherapp.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicdamun.common.models.LocationModel
import com.nicdamun.domain.useCases.GetLocationsByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationsByNameUseCase: GetLocationsByNameUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>(HomeViewState.OnNoLocations)
    val state: LiveData<HomeViewState> = _state

    fun getLocationsByName(locationName: String) {
        viewModelScope.launch {
            _state.value = HomeViewState.Loading
            _state.value = getLocationsByNameUseCase.invoke(locationName).getOrNull()?.let {  locations ->
                if (locations.isEmpty()) HomeViewState.OnNoLocations
                else HomeViewState.OnLocationsLoaded(locations)
            } ?: HomeViewState.OnLocationsFailed
        }
    }

    fun onLocationSelected(locationModel: LocationModel) {

    }

}