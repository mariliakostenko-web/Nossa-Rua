package com.upx.nossarua.presentation.ui.screen.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.upx.nossarua.domain.model.BottomBarItems
import com.upx.nossarua.presentation.navigation.AppDirections
import com.upx.nossarua.presentation.theme.NossaRuaTheme
import com.upx.nossarua.presentation.ui.common.BottomBar
import com.upx.nossarua.presentation.ui.common.TopBar

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    NossaRuaTheme {
        Scaffold(
            modifier = modifier.background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBar()
            },
            bottomBar = {
                BottomBar(
                    selectedItem = BottomBarItems.MAP,
                    onClick = {
                        navController.navigate(AppDirections.Map)
                    }
                )
            }
        ) { contentPadding ->
            Column(Modifier.padding(contentPadding)) {
                // Initialize the camera position state, which controls the camera's position on the map
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(51.5074, -0.1278), 10f)
                }

                // Display the Google Map without
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                )
            }
        }
    }
}