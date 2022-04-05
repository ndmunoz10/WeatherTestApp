package com.nicdamun.domain.usecases

import com.nicdamun.domain.mocks.normalLocationDetailsModel
import com.nicdamun.domain.useCases.GetLocationDetailsUseCase
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

class GetLocationDetailsUseCaseTest {

    private val mockLocationRepository = mockk<ILocationRepository>(relaxed = true)
    private val subject = GetLocationDetailsUseCase(mockLocationRepository)
    private val woeid = 1234

    @After
    fun tearDown() {
        confirmVerified(mockLocationRepository)
    }

    @Test
    fun `when the use case is called, a result containing a location details should be returned`() {
        coEvery { mockLocationRepository.getLocationDetails(woeid) } returns Result.success(normalLocationDetailsModel)

        runBlocking {
            val result = subject.invoke(woeid)

            coVerify { mockLocationRepository.getLocationDetails(woeid) }

            expectThat(result.getOrNull()) isEqualTo normalLocationDetailsModel
        }
    }
}