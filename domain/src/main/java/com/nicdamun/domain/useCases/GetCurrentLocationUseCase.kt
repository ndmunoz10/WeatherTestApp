package com.nicdamun.domain.useCases

import com.nicdamun.common.models.DeviceLocationModel
import com.nicdamun.repository.ILocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: ILocationRepository
) {

    suspend fun invoke(): DeviceLocationModel? {
        return locationRepository.getCurrentDeviceLocation()
    }
}