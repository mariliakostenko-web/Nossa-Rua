package com.upx.nossarua.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.presentation.navigation.AppNavHost
import com.upx.nossarua.presentation.ui.screen.error.ErrorScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the fusedLocationClient here
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation { userLocation ->
                showMap(userLocation)
            }
        } else {
            // Request location permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun getCurrentLocation(onLocationRetrieved: (LatLng) -> Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                onLocationRetrieved(LatLng(it.latitude, it.longitude))
            } ?: run {
                // Handle case where location is null (e.g., default to a specific location)
                onLocationRetrieved(LatLng(0.0, 0.0)) // Or handle appropriately
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    getCurrentLocation { showMap(it) }
                } else {
                    showError()
                }
            } else {
                showError()
            }
        } else {
            showError()
        }
    }

    private fun showMap(userLocation: LatLng) {
        setContent {
            AppNavHost(userLocation)
        }
    }

    private fun showError() {
        setContent {
            ErrorScreen(
                navController = null,
                errorMessage = "Permita o acesso a localização para utilizar o App!"
            )
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000

    }
}