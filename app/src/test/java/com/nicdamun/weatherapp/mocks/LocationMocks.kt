package com.nicdamun.weatherapp.mocks

import com.nicdamun.common.dtos.LocationDTO
import com.nicdamun.common.models.LocationModel

val locationDTO1 = LocationDTO(
    title = "someTitle",
    locationType = "someLocationType",
    woeid = 1,
    lattLong = "1,1"
)

val locationDTO2 = LocationDTO(
    title = "someTitle",
    locationType = "someLocationType",
    woeid = 2,
    lattLong = "1,1"
)

val locationDTOs = listOf(locationDTO1, locationDTO2)

val locationModel1 = LocationModel(
    title = "someTitle",
    locationType = "someLocationType",
    woeid = 1,
    latitude = 1.0,
    longitude = 1.0
)

val locationModel2 = LocationModel(
    title = "someTitle",
    locationType = "someLocationType",
    woeid = 2,
    latitude = 1.0,
    longitude = 1.0
)

val locationModels = listOf(locationModel1, locationModel2)