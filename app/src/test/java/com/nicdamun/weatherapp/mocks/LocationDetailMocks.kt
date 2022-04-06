package com.nicdamun.weatherapp.mocks

import com.nicdamun.common.dtos.LocationDetailDTO
import com.nicdamun.common.models.LocationDetailModel

val normalLocationDetailDTO = LocationDetailDTO(
    title = "someTitle",
    locationType = "someType",
    woeid = 1,
    timezone = "someTimezone",
    consolidatedWeather = emptyList()
)

val normalLocationDetailModel = LocationDetailModel(
    title = "someTitle",
    locationType = "someType",
    timezone = "someTimezone",
    consolidatedWeather = emptyList()
)