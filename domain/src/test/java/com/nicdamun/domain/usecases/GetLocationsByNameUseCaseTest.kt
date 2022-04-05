package com.nicdamun.domain.usecases

import com.nicdamun.domain.mocks.locationModels
import com.nicdamun.domain.mocks.normalDeviceLocation
import com.nicdamun.domain.useCases.CalculateDistanceUseCase
import com.nicdamun.domain.useCases.GetCurrentLocationUseCase
import com.nicdamun.domain.useCases.GetLocationsByNameUseCase
import com.nicdamun.repository.ILocationRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

class GetLocationsByNameUseCaseTest {

    private val mockLocationRepository = mockk<ILocationRepository>(relaxed = true)
    private val mockCalculateDistanceUseCase = mockk<CalculateDistanceUseCase>(relaxed = true)
    private val mockGetCurrentLocationUseCase = mockk<GetCurrentLocationUseCase>(relaxed = true)

    private val subject = GetLocationsByNameUseCase(
        mockCalculateDistanceUseCase,
        mockGetCurrentLocationUseCase,
        mockLocationRepository,
    )
    private val locationName = "someLocationName"

    @After
    fun tearDown() {
        confirmVerified(
            mockLocationRepository,
            mockCalculateDistanceUseCase,
            mockGetCurrentLocationUseCase
        )
    }

    @Test
    fun `when use case is called and the current device location is null then no action is done`() {
        coEvery { mockGetCurrentLocationUseCase.invoke() } returns null
        coEvery { mockLocationRepository.getLocationsByName(locationName) } returns Result.success(
            locationModels
        )

        runBlocking {
            val result = subject.invoke(locationName)

            coVerify {
                mockLocationRepository.getLocationsByName(locationName)
                mockGetCurrentLocationUseCase.invoke()
            }

            expectThat(result.getOrNull())
                .isEqualTo(locationModels)
        }
    }

    @Test
    fun `when use case is called and the current device location is not null then the distance is calculated for each location in the response list`() {
        coEvery { mockGetCurrentLocationUseCase.invoke() } returns normalDeviceLocation
        coEvery { mockLocationRepository.getLocationsByName(locationName) } returns Result.success(
            locationModels
        )

        runBlocking {
            val result = subject.invoke(locationName)

            coVerify {
                mockLocationRepository.getLocationsByName(locationName)
                mockGetCurrentLocationUseCase.invoke()
            }
            coVerify(exactly = 2) {
                mockCalculateDistanceUseCase.invoke(1.0, 1.0, 1.2, 1.4)
            }

            val locations = result.getOrNull().orEmpty()
            locations.forEach { location ->
                expectThat(location.distance).isNotNull()
            }
        }
    }
}