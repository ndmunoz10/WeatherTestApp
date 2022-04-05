package com.nicdamun.weatherapp.data.repository

import com.nicdamun.common.mappers.toDeviceLocationModel
import com.nicdamun.common.mappers.toLocationDetailModel
import com.nicdamun.common.models.DeviceLocationModel
import com.nicdamun.common.models.LocationModel
import com.nicdamun.datasource.ILocationLocalDataSource
import com.nicdamun.datasource.ILocationRemoteDataSource
import com.nicdamun.repository.ILocationRepository
import com.nicdamun.common.mappers.toLocationModel
import com.nicdamun.common.models.LocationDetailModel

class LocationRepository(
    private val localDataSource: ILocationLocalDataSource,
    private val remoteDataSource: ILocationRemoteDataSource
): ILocationRepository {

    override suspend fun getCurrentDeviceLocation(): DeviceLocationModel? {
        return localDataSource.getCurrentDeviceLocation()?.toDeviceLocationModel()
    }

    override suspend fun getLocationDetails(woeid: Int): Result<LocationDetailModel> {
        return remoteDataSource.getLocationDetails(woeid).map { result ->
            result.toLocationDetailModel()
        }
    }

    override suspend fun getLocationsByName(locationName: String): Result<List<LocationModel>> {
        return remoteDataSource.getLocationsByName(locationName).map { result ->
            result.mapNotNull { it.toLocationModel() }
        }
    }

}