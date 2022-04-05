package com.nicdamun.domain.di

import com.nicdamun.domain.useCases.CalculateDistanceUseCase
import com.nicdamun.domain.useCases.GetCurrentLocationUseCase
import com.nicdamun.domain.useCases.GetLocationDetailsUseCase
import com.nicdamun.domain.useCases.GetLocationsByNameUseCase
import com.nicdamun.repository.ILocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideCalculateDistanceUseCase(): CalculateDistanceUseCase {
        return CalculateDistanceUseCase()
    }

    @Provides
    fun provideGetCurrentLocationUseCase(
        locationRepository: ILocationRepository
    ): GetCurrentLocationUseCase {
        return GetCurrentLocationUseCase(locationRepository)
    }

    @Provides
    fun providesGetLocationDetailsUseCase(
        locationRepository: ILocationRepository
    ): GetLocationDetailsUseCase {
        return GetLocationDetailsUseCase(locationRepository)
    }

    @Provides
    fun provideGetLocationsByNameUseCase(
        calculateDistanceUseCase: CalculateDistanceUseCase,
        getCurrentLocationUseCase: GetCurrentLocationUseCase,
        locationRepository: ILocationRepository
    ): GetLocationsByNameUseCase {
        return GetLocationsByNameUseCase(
            calculateDistanceUseCase,
            getCurrentLocationUseCase,
            locationRepository
        )
    }
}