package com.nicdamun.common.models

data class ConsolidatedWeatherModel(
    val id: Long,
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val windDirectionCompass: String,
    val minTemp: Double,
    val maxTemp: Double,
    val theTemp: Double,
    val predictability: Int,
    val applicableDate: String
)
