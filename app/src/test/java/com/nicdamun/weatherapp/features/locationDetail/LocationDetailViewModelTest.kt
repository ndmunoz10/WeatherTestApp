package com.nicdamun.weatherapp.features.locationDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicdamun.domain.useCases.GetLocationDetailsUseCase
import com.nicdamun.weatherapp.mocks.normalLocationDetailModel
import com.nicdamun.weatherapp.utils.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class LocationDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockGetLocationDetailsUseCase = mockk<GetLocationDetailsUseCase>(relaxed = true)
    private lateinit var subject: LocationDetailViewModel
    private val mockWoeid = 1

    @Before
    fun setup() {
        subject = LocationDetailViewModel(
            mockGetLocationDetailsUseCase
        )
    }

    @After
    fun tearDown() {
        confirmVerified(mockGetLocationDetailsUseCase)
    }

    @Test
    fun `when getLocationDetails is called, OnLocationDetailRetrieved is emitted`() {
        coEvery { mockGetLocationDetailsUseCase.invoke(mockWoeid) } returns Result.success(normalLocationDetailModel)

        runBlocking {
            subject.getLocationDetails(mockWoeid)

            coVerify { mockGetLocationDetailsUseCase.invoke(mockWoeid) }

            expectThat(subject.state.value).isEqualTo(
                LocationDetailViewState.OnLocationDetailRetrieved(normalLocationDetailModel)
            )
        }
    }

    @Test
    fun `when getLocationDetails is called, OnLocationDetailFailed is emitted`() {
        coEvery { mockGetLocationDetailsUseCase.invoke(mockWoeid) } returns Result.failure(Exception("Some error"))

        subject.getLocationDetails(mockWoeid)

        coVerify { mockGetLocationDetailsUseCase.invoke(mockWoeid) }

        expectThat(subject.state.value).isA<LocationDetailViewState.OnLocationDetailFailed>()
    }
}