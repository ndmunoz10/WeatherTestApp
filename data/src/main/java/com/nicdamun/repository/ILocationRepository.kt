package com.nicdamun.repository

import com.nicdamun.common.models.DeviceLocationModel
import com.nicdamun.common.models.LocationDetailModel
import com.nicdamun.common.models.LocationModel

interface ILocationRepository {

    suspend fun getCurrentDeviceLocation(): DeviceLocationModel?

    suspend fun getLocationDetails(woeid: Int): Result<LocationDetailModel>

    suspend fun getLocationsByName(locationName: String): Result<List<LocationModel>>
}