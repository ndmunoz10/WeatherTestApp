package com.nicdamun.common.mappers

import com.nicdamun.common.dtos.ConsolidatedWeatherDTO
import com.nicdamun.common.models.ConsolidatedWeatherModel

fun ConsolidatedWeatherDTO.toConsolidatedWeatherModel(): ConsolidatedWeatherModel {
    return ConsolidatedWeatherModel(
        weatherStateName.orEmpty(),
        weatherStateAbbr.orEmpty(),
        windDirectionCompass.orEmpty(),
        minTemp ?: 0.0,
        maxTemp ?: 0.0,
        theTemp ?: 0.0,
        predictability ?: 0
    )
}