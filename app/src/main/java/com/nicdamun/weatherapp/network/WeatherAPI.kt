package com.nicdamun.weatherapp.network

import com.nicdamun.common.dtos.LocationDTO
import com.nicdamun.common.dtos.LocationDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {

    @GET("location/search")
    suspend fun getLocationsByName(
        @Query("query") locationName: String
    ): Response<List<LocationDTO>>

    @GET("location/{woeid}")
    suspend fun getLocationDetails(
        @Path("woeid") woeid: Int
    ): Response<LocationDetailDTO>
}