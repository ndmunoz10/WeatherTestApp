package com.nicdamun.weatherapp.utils

import androidx.annotation.RawRes
import com.nicdamun.weatherapp.R
import com.nicdamun.weatherapp.common.GeneralConstants

object WeatherUtils {

    @RawRes
    fun setWeatherAnimation(weatherType: String): Int {
        return when (weatherType) {
            GeneralConstants.SNOW_CODE -> R.raw.snow_anim
            GeneralConstants.SLEET_CODE -> R.raw.sleet
            GeneralConstants.HAIL_CODE -> R.raw.heavy_rain
            GeneralConstants.THUNDERSTORM_CODE -> R.raw.thunderstorm
            GeneralConstants.HEAVY_RAIN -> R.raw.heavy_rain
            GeneralConstants.LIGHT_RAIN -> R.raw.light_rain
            GeneralConstants.SHOWERS -> R.raw.heavy_rain
            GeneralConstants.HEAVY_CLOUD_CODE -> R.raw.heavy_clouds
            GeneralConstants.LIGHT_CLOUD_CODE -> R.raw.light_clouds
            else -> R.raw.clear_sky
        }
    }
}