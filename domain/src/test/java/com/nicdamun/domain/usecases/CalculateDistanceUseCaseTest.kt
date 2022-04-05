package com.nicdamun.domain.usecases

import com.nicdamun.domain.useCases.CalculateDistanceUseCase
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isGreaterThan
import strikt.assertions.isNotNull
import strikt.assertions.isNull

class CalculateDistanceUseCaseTest {

    private val subject = CalculateDistanceUseCase()

    @Test
    fun `when use case is called, the distance between two coordinates should be returned`() {
        val result = subject.invoke(49.988089, 14.399137, 49.979480, 14.398794)

        expectThat(result).isNotNull().and { this isGreaterThan 0.5 }
    }

    @Test
    fun `when use case is called and latitude 1 is null, null should be returned`() {
        val result = subject.invoke(null, 14.399137, 49.979480, 14.398794)

        expectThat(result).isNull()
    }

    @Test
    fun `when use case is called and latitude 2 is null, null should be returned`() {
        val result = subject.invoke(49.988089, null, 49.979480, 14.398794)

        expectThat(result).isNull()
    }

    @Test
    fun `when use case is called and latitude 3 is null, null should be returned`() {
        val result = subject.invoke(49.988089, 14.399137, null, 14.398794)

        expectThat(result).isNull()
    }

    @Test
    fun `when use case is called and latitude 4 is null, null should be returned`() {
        val result = subject.invoke(49.988089, 14.399137, 49.979480, null)

        expectThat(result).isNull()
    }

}