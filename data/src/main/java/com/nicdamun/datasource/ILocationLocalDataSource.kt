package com.nicdamun.datasource

import com.nicdamun.common.dtos.DeviceLocationDTO

interface ILocationLocalDataSource {

    suspend fun getCurrentDeviceLocation(): DeviceLocationDTO?
}