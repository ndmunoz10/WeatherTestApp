package com.nicdamun.domain.mocks

import com.nicdamun.common.models.LocationModel

val location1 = LocationModel(
    title = "someTitle1",
    locationType = "someType1",
    woeid = 1,
    latitude = 1.0,
    longitude = 1.0
)

val location2 = LocationModel(
    title = "someTitle2",
    locationType = "someType2",
    woeid = 2,
    latitude = 1.0,
    longitude = 1.0
)

val locationModels = listOf(
    location1,
    location2
)