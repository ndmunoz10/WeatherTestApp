package com.nicdamun.weatherapp.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.nicdamun.weatherapp.BuildConfig
import com.nicdamun.weatherapp.network.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideWeatherAPI(): WeatherAPI {
        return provideRetrofitInstance()
            .create(WeatherAPI::class.java)
    }

    private fun provideRetrofitInstance(): Retrofit {
        val gsonBuilder = GsonBuilder().apply {
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }
        val interceptor = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
    }
}