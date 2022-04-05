package com.nicdamun.weatherapp.di

import android.content.Context
import com.nicdamun.datasource.ILocationLocalDataSource
import com.nicdamun.datasource.ILocationRemoteDataSource
import com.nicdamun.weatherapp.data.dataSource.LocationLocalDataSource
import com.nicdamun.weatherapp.data.dataSource.LocationRemoteDataSource
import com.nicdamun.weatherapp.network.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun providesLocationLocalDataSource(
        @ApplicationContext context: Context
    ): ILocationLocalDataSource {
        return LocationLocalDataSource(context)
    }

    @Provides
    fun provideLocationRemoteDataSource(
        weatherAPI: WeatherAPI
    ): ILocationRemoteDataSource {
        return LocationRemoteDataSource(weatherAPI)
    }
}