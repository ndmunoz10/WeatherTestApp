package com.nicdamun.domain.useCases

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class CalculateDistanceUseCase {

    fun invoke(lat1: Double?, lon1: Double?, lat2: Double?, lon2: Double?): Double? {
        return if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) {
            null
        } else {
            val theta: Double = lon1 - lon2
            var dist = (sin(deg2rad(lat1))
                    * sin(deg2rad(lat2))
                    + (cos(deg2rad(lat1))
                    * cos(deg2rad(lat2))
                    * cos(deg2rad(theta))))
            dist = acos(dist)
            dist = rad2deg(dist)
            dist *= 60 * 1.1515
            dist
        }
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}