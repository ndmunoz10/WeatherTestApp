package com.nicdamun.weatherapp.utils

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

object PermissionsUtils {

    fun requestForPermission(activity: ComponentActivity, vararg permissions: String) {
        val locationPermissionRequest = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { requestedPermissions ->
            when {
                requestedPermissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    println("Permission granted!")
                    // I don't care about the response here
                }
                else -> {
                    println("Permission not granted!")
                    // I don't care about the response here
                }
            }
        }
        locationPermissionRequest.launch(arrayOf(*permissions))
    }
}