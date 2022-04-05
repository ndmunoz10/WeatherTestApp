package com.nicdamun.weatherapp.di

import com.nicdamun.datasource.ILocationLocalDataSource
import com.nicdamun.datasource.ILocationRemoteDataSource
import com.nicdamun.repository.ILocationRepository
import com.nicdamun.weatherapp.data.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesLocationRepository(
        locationLocalDataSource: ILocationLocalDataSource,
        locationRemoteDataSource: ILocationRemoteDataSource
    ): ILocationRepository {
        return LocationRepository(locationLocalDataSource, locationRemoteDataSource)
    }
}