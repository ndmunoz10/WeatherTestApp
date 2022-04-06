package com.nicdamun.domain.useCases

import com.nicdamun.common.models.LocationDetailModel
import com.nicdamun.repository.ILocationRepository

class GetLocationDetailsUseCase(
    private val locationRepository: ILocationRepository
) {

    suspend fun invoke(woeid: Int): Result<LocationDetailModel> {
        return locationRepository.getLocationDetails(woeid)
    }
}