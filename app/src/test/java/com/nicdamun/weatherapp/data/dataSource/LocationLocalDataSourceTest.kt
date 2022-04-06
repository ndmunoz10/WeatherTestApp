package com.nicdamun.weatherapp.data.dataSource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import com.nicdamun.common.dtos.DeviceLocationDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class LocationLocalDataSourceTest {

    private val mockContext = mockk<Context>(relaxed = true)
    private val mockLocationClient = mockk<FusedLocationProviderClient>(relaxed = true)
    private val subject = LocationLocalDataSource(mockContext)

    @Before
    fun setup() {
        mockkStatic(ActivityCompat::class)
        mockkStatic(LocationServices::class)
    }

    @Test
    fun `when getCurrentDeviceLocation is called and location permission is denied, null must be returned`() {
        every {
            ActivityCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_COARSE_LOCATION)
        } returns PackageManager.PERMISSION_DENIED

        runBlocking {
            val result = subject.getCurrentDeviceLocation()

            expectThat(result).isNull()
        }
    }

    @Test
    fun `when getCurrentDeviceLocation is called and location permission is granted, null must be returned`() {
        every {
            ActivityCompat.checkSelfPermission(mockContext, Manifest.permission.ACCESS_COARSE_LOCATION)
        } returns PackageManager.PERMISSION_GRANTED

        every {
            LocationServices.getFusedLocationProviderClient(mockContext)
        } returns mockLocationClient

        every {
            mockLocationClient.lastLocation
        } returns Tasks.forResult(Location(""))

        runBlocking {
            val result = subject.getCurrentDeviceLocation()

            expectThat(result).isEqualTo(DeviceLocationDTO(0.0, 0.0))
        }
    }
}