package com.nicdamun.common.dtos

data class ConsolidatedWeatherDTO(
    val id: Long,
    val weatherStateName: String?,
    val weatherStateAbbr: String?,
    val windDirectionCompass: String?,
    val minTemp: Double?,
    val maxTemp: Double?,
    val theTemp: Double?,
    val predictability: Int?,
    val applicableDate: String?
)
