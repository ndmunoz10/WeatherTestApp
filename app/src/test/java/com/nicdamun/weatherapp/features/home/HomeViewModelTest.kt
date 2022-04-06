package com.nicdamun.weatherapp.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicdamun.domain.useCases.GetLocationsByNameUseCase
import com.nicdamun.weatherapp.mocks.locationModels
import com.nicdamun.weatherapp.utils.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockGetLocationsByNameUseCase = mockk<GetLocationsByNameUseCase>(relaxed = true)
    private lateinit var subject: HomeViewModel
    private val mockName = "someName"

    @Before
    fun setup() {
        subject = HomeViewModel(mockGetLocationsByNameUseCase)
    }

    @After
    fun tearDown() {
        confirmVerified(mockGetLocationsByNameUseCase)
    }

    @Test
    fun `when getLocationsByName is called OnLocationsLoaded is emitted`() {
        coEvery { mockGetLocationsByNameUseCase.invoke(mockName) } returns Result.success(
            locationModels
        )

        subject.getLocationsByName(mockName)

        coVerify { mockGetLocationsByNameUseCase.invoke(mockName) }

        expectThat(subject.state.value).isA<HomeViewState.OnLocationsLoaded>()
    }

    @Test
    fun `when getLocationsByName is called and the location list is empty then OnNoLocations is emitted`() {
        coEvery { mockGetLocationsByNameUseCase.invoke(mockName) } returns Result.success(emptyList())

        subject.getLocationsByName(mockName)

        coVerify { mockGetLocationsByNameUseCase.invoke(mockName) }

        expectThat(subject.state.value).isA<HomeViewState.OnNoLocations>()
    }

    @Test
    fun `when getLocationsByName is called and the result has a failure then OnLocationsFailed is emitted`() {
        coEvery { mockGetLocationsByNameUseCase.invoke(mockName) } returns Result.failure(Exception("Some exception"))

        subject.getLocationsByName(mockName)

        coVerify { mockGetLocationsByNameUseCase.invoke(mockName) }

        expectThat(subject.state.value).isA<HomeViewState.OnLocationsFailed>()
    }
}