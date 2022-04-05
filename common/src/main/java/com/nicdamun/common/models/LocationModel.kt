package com.nicdamun.common.models

data class LocationModel(
    val title: String,
    val locationType: String,
    val woeid: Int,
    val latitude: Double?,
    val longitude: Double?,
    val distance: Double? = null
)
