package com.nicdamun.datasource

import com.nicdamun.common.dtos.LocationDTO
import com.nicdamun.common.dtos.LocationDetailDTO

interface ILocationRemoteDataSource {

    suspend fun getLocationDetails(woeid: Int): Result<LocationDetailDTO>

    suspend fun getLocationsByName(locationName: String): Result<List<LocationDTO>>
}