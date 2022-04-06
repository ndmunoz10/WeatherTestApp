package com.nicdamun.common.mappers

import com.nicdamun.common.dtos.DeviceLocationDTO
import com.nicdamun.common.dtos.LocationDTO
import com.nicdamun.common.models.DeviceLocationModel
import com.nicdamun.common.models.LocationModel

fun LocationDTO.toLocationModel(): LocationModel? {
    val actualWoeid = woeid
    return if (actualWoeid == null) {
        null
    } else {
        val coordinates = getLatLonFromString(lattLong.orEmpty())
        LocationModel(
            title = title.orEmpty(),
            woeid = actualWoeid,
            locationType = locationType.orEmpty(),
            latitude = coordinates?.first,
            longitude = coordinates?.second
        )
    }
}

private fun getLatLonFromString(latLong: String): Pair<Double, Double>? {
    val stringCoordinates = latLong.split(",")
    val lat = stringCoordinates.getOrNull(0)?.toDoubleOrNull()
    val lon = stringCoordinates.getOrNull(1)?.toDoubleOrNull()
    return if (lat == null || lon == null) {
        null
    } else {
        Pair(lat, lon)
    }
}

fun DeviceLocationDTO.toDeviceLocationModel(): DeviceLocationModel {
    return DeviceLocationModel(
        latitude,
        longitude
    )
}