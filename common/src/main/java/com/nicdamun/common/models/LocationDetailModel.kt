package com.nicdamun.common.models

data class LocationDetailModel(
    val title: String,
    val locationType: String,
    val timezone: String,
    val consolidatedWeather: List<ConsolidatedWeatherModel>
)
