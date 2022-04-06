package com.nicdamun.weatherapp.data.dataSource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.nicdamun.common.dtos.DeviceLocationDTO
import com.nicdamun.datasource.ILocationLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LocationLocalDataSource(
    private val context: Context
) : ILocationLocalDataSource {

    override suspend fun getCurrentDeviceLocation(): DeviceLocationDTO? {
        val permissionCheckStatus = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return if (permissionCheckStatus != PackageManager.PERMISSION_GRANTED) null
        else {
            withContext(Dispatchers.IO) {
                runCatching {
                    val location = LocationServices.getFusedLocationProviderClient(context)
                        .lastLocation
                        .await()
                    DeviceLocationDTO(
                        location.latitude,
                        location.longitude
                    )
                }.getOrNull()
            }
        }
    }
}