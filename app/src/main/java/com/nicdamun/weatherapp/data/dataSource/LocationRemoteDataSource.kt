package com.nicdamun.weatherapp.data.dataSource

import com.nicdamun.datasource.ILocationRemoteDataSource
import com.nicdamun.common.dtos.LocationDTO
import com.nicdamun.common.dtos.LocationDetailDTO
import com.nicdamun.weatherapp.extensions.safeAPICall
import com.nicdamun.weatherapp.network.WeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRemoteDataSource(
    private val weatherAPI: WeatherAPI
) : ILocationRemoteDataSource {

    override suspend fun getLocationDetails(woeid: Int): Result<LocationDetailDTO> {
        return withContext(Dispatchers.IO) {
            safeAPICall { weatherAPI.getLocationDetails(woeid) }
        }
    }

    override suspend fun getLocationsByName(locationName: String): Result<List<LocationDTO>> {
        return withContext(Dispatchers.IO) {
            safeAPICall { weatherAPI.getLocationsByName(locationName) }
        }
    }
}