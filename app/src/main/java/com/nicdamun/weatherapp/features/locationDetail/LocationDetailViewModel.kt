package com.nicdamun.weatherapp.features.locationDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicdamun.domain.useCases.GetLocationDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase
): ViewModel() {

    private val _state = MutableLiveData<LocationDetailViewState>(LocationDetailViewState.Loading)
    val state: LiveData<LocationDetailViewState> = _state

    fun getLocationDetails(woeid: Int) {
        viewModelScope.launch {
            _state.value = getLocationDetailsUseCase.invoke(woeid)
                .getOrNull()?.let { locationDetailModel ->
                    LocationDetailViewState.OnLocationDetailRetrieved(locationDetailModel)
                } ?: LocationDetailViewState.OnLocationDetailFailed
        }
    }
}