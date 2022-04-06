package com.nicdamun.common.dtos

data class LocationDetailDTO(
    val title: String?,
    val locationType: String?,
    val woeid: Int?,
    val timezone: String?,
    val consolidatedWeather: List<ConsolidatedWeatherDTO>?
)
