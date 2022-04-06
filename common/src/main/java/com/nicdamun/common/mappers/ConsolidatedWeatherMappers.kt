package com.nicdamun.common.mappers

import com.nicdamun.common.dtos.ConsolidatedWeatherDTO
import com.nicdamun.common.models.ConsolidatedWeatherModel
import com.nicdamun.weatherapp.extensions.roundToOneDecimalPlace

fun ConsolidatedWeatherDTO.toConsolidatedWeatherModel(): ConsolidatedWeatherModel {
    return ConsolidatedWeatherModel(
        id,
        weatherStateName.orEmpty(),
        weatherStateAbbr.orEmpty(),
        windDirectionCompass.orEmpty(),
        minTemp ?: 0.0,
        maxTemp ?: 0.0,
        theTemp?.roundToOneDecimalPlace() ?: 0.0,
        predictability ?: 0,
        applicableDate.orEmpty()
    )
}