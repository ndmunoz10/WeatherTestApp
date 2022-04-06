package com.nicdamun.domain.mocks

import com.nicdamun.common.models.LocationDetailModel

val normalLocationDetailsModel = LocationDetailModel(
    title = "someTitle",
    locationType = "someType",
    timezone = "someTimezone",
    consolidatedWeather = emptyList()
)