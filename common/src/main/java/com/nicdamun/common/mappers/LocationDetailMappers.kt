package com.nicdamun.common.mappers

import com.nicdamun.common.dtos.LocationDetailDTO
import com.nicdamun.common.models.LocationDetailModel

fun LocationDetailDTO.toLocationDetailModel(): LocationDetailModel {
    return LocationDetailModel(
        title = title.orEmpty(),
        locationType = locationType.orEmpty(),
        timezone = timezone.orEmpty(),
        consolidatedWeather = consolidatedWeather?.map { consolidatedWeatherDTO ->
            consolidatedWeatherDTO.toConsolidatedWeatherModel()
        }.orEmpty()
    )
}