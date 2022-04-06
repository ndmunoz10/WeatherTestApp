package com.nicdamun.weatherapp.data.dataSource

import com.nicdamun.weatherapp.data.dataSource.LocationRemoteDataSource
import com.nicdamun.weatherapp.mocks.locationDTOs
import com.nicdamun.weatherapp.mocks.normalLocationDetailDTO
import com.nicdamun.weatherapp.network.WeatherAPI
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class LocationRemoteDataSourceTest {

    private val mockWeatherAPI = mockk<WeatherAPI>(relaxed = true)
    private val subject = LocationRemoteDataSource(mockWeatherAPI)
    private val mockWoeid = 1234
    private val mockName = "someName"

    @After
    fun tearDown() {
        confirmVerified(mockWeatherAPI)
    }

    @Test
    fun `when getLocationDetails is called, a result containing the response should be returned`() {
        coEvery { mockWeatherAPI.getLocationDetails(mockWoeid) } returns Response.success(
            normalLocationDetailDTO
        )

        runBlocking {
            val result = subject.getLocationDetails(mockWoeid)

            coVerify { mockWeatherAPI.getLocationDetails(mockWoeid) }

            expectThat(result.getOrNull()).isEqualTo(normalLocationDetailDTO)
        }
    }

    @Test
    fun `when getLocationsByName is called, a result containing the response should be returned`() {
        coEvery { mockWeatherAPI.getLocationsByName(mockName) } returns Response.success(
            locationDTOs
        )

        runBlocking {
            val result = subject.getLocationsByName(mockName)

            coVerify { mockWeatherAPI.getLocationsByName(mockName) }

            expectThat(result.getOrNull()).isEqualTo(locationDTOs)
        }
    }
}