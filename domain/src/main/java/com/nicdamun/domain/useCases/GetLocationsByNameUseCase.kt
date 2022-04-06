package com.nicdamun.domain.useCases

import com.nicdamun.common.models.LocationModel
import com.nicdamun.repository.ILocationRepository
import javax.inject.Inject

class GetLocationsByNameUseCase @Inject constructor(
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val locationRepository: ILocationRepository
) {

    suspend fun invoke(locationName: String): Result<List<LocationModel>> {
        val deviceLocation = getCurrentLocationUseCase.invoke()
        val locationResult = locationRepository.getLocationsByName(locationName)
        return if (deviceLocation == null) {
            locationResult
        } else {
            val locations = locationResult.getOrDefault(emptyList())
            val newLocationResult = locations.map { locationModel ->
                locationModel.copy(
                    distance = calculateDistanceUseCase.invoke(
                        locationModel.latitude,
                        locationModel.longitude,
                        deviceLocation.latitude,
                        deviceLocation.longitude
                    )
                )
            }
            Result.success(newLocationResult)
        }
    }
}