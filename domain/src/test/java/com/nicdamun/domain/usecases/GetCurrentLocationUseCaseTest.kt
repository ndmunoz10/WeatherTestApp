package com.nicdamun.domain.usecases

import com.nicdamun.domain.mocks.normalDeviceLocation
import com.nicdamun.domain.useCases.GetCurrentLocationUseCase
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

class GetCurrentLocationUseCaseTest {

    private val mockLocationRepository = mockk<ILocationRepository>(relaxed = true)
    private val subject = GetCurrentLocationUseCase(mockLocationRepository)

    @After
    fun tearDown() {
        confirmVerified(mockLocationRepository)
    }

    @Test
    fun `when use case is called, a device location is returned`() {
        coEvery { mockLocationRepository.getCurrentDeviceLocation() } returns normalDeviceLocation

        runBlocking {
            val result = subject.invoke()

            coVerify { mockLocationRepository.getCurrentDeviceLocation() }

            expectThat(result).isEqualTo(normalDeviceLocation)
        }
    }

}