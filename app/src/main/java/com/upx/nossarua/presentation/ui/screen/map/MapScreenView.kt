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
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.upx.nossarua.domain.model.BottomBarItems
import com.upx.nossarua.presentation.navigation.AppDirections
import com.upx.nossarua.presentation.theme.NossaRuaTheme
import com.upx.nossarua.presentation.ui.common.BottomBar
import com.upx.nossarua.presentation.ui.common.MapMarkerComposable
import com.upx.nossarua.presentation.ui.common.TopBar
import com.upx.nossarua.presentation.ui.screen.BaseViewModel
import com.upx.nossarua.presentation.ui.screen.create.MessageDialog

@Composable
fun MapScreen(
    viewModel: BaseViewModel,
    navController: NavController,
    userLocation: LatLng
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.retrieveMarkers()
    NossaRuaTheme {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBar(
                    screenTitle = "Mapa de Incidentes",
                    onListClicked = {
                        navController.navigate(AppDirections.List.route)
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    selectedItem = BottomBarItems.MAP,
                    onClick = { bottomBarItems ->
                        when (bottomBarItems) {
                            BottomBarItems.MAP -> {}

                            BottomBarItems.CREATE -> navController.navigate(AppDirections.Create.route)
                        }
                    }
                )
            }
        ) { contentPadding ->
            Column(Modifier.padding(contentPadding)) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition(
                        userLocation,
                        15f,
                        0f,
                        0f
                    )
                }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = true)
                ) {
                    MapMarkerComposable(
                        markers = uiState.marker
                    )
                }
                MessageDialog(
                    isVisible = uiState.showDialog,
                    onDismiss = {
                        viewModel.onCloseDialog()
                    },
                    message = uiState.message,
                    isSuccess = false
                )
            }
        }
    }
}
