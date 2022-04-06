package com.nicdamun.weatherapp.data.repository

import com.nicdamun.datasource.ILocationLocalDataSource
import com.nicdamun.datasource.ILocationRemoteDataSource
import com.nicdamun.weatherapp.mocks.locationDTOs
import com.nicdamun.weatherapp.mocks.normalDeviceLocationDTO
import com.nicdamun.weatherapp.mocks.normalDeviceLocationModel
import com.nicdamun.weatherapp.mocks.normalLocationDetailDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class LocationRepositoryTest {

    private val mockLocalDataSource = mockk<ILocationLocalDataSource>(relaxed = true)
    private val mockRemoteDataSource = mockk<ILocationRemoteDataSource>(relaxed = true)
    private val subject = LocationRepository(
        mockLocalDataSource,
        mockRemoteDataSource
    )
    private val mockName = "someName"
    private val mockWoeid = 1234

    @After
    fun tearDown() {
        confirmVerified(
            mockLocalDataSource,
            mockRemoteDataSource
        )
    }

    @Test
    fun `when getCurrentDeviceLocation is called the current device location is mapped and returned`() {
        coEvery { mockLocalDataSource.getCurrentDeviceLocation() } returns normalDeviceLocationDTO

        runBlocking {
            val result = subject.getCurrentDeviceLocation()

            coVerify { mockLocalDataSource.getCurrentDeviceLocation() }

            expectThat(result).isEqualTo(normalDeviceLocationModel)
        }
    }

    @Test
    fun `when getLocationDetails is called a result containing the location details is returned`() {
        coEvery { mockRemoteDataSource.getLocationDetails(mockWoeid) } returns Result.success(
            normalLocationDetailDTO
        )

        runBlocking {
            val result = subject.getLocationDetails(mockWoeid)

            coVerify { mockRemoteDataSource.getLocationDetails(mockWoeid) }

            expectThat(result).isSuccess()

        }
    }

    @Test
    fun `when getLocationsByName is called a result containing the list of locations is returned`() {
        coEvery { mockRemoteDataSource.getLocationsByName(mockName) } returns Result.success(locationDTOs)

        runBlocking {
            val result = subject.getLocationsByName(mockName)

            coVerify { mockRemoteDataSource.getLocationsByName(mockName) }

            expectThat(result).isSuccess()
        }
    }
}