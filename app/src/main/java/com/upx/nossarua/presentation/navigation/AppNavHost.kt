package com.upx.nossarua.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.presentation.ui.screen.BaseViewModel
import com.upx.nossarua.presentation.ui.screen.create.CreateScreenView
import com.upx.nossarua.presentation.ui.screen.error.ErrorScreen
import com.upx.nossarua.presentation.ui.screen.list.ListScreen
import com.upx.nossarua.presentation.ui.screen.map.MapScreen

@Composable
fun AppNavHost(
    userLocation: LatLng
) {
    val baseViewModel: BaseViewModel = hiltViewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppDirections.Map.route
    ) {
        composable(
            route = AppDirections.Map.route
        ) { navBackStackEntry ->
            MapScreen(
                viewModel = baseViewModel,
                navController = navController,
                userLocation = userLocation
            )
        }
        composable(
            route = AppDirections.Create.route
        ) { navBackStackEntry ->
            CreateScreenView(
                viewModel = hiltViewModel(),
                navController = navController,
                userLocation = userLocation,
            )
        }
        composable(
            route = AppDirections.List.route
        ) { navBackStackEntry ->
            ListScreen(
                navController = navController,
                viewModel = baseViewModel,
                userLocation = userLocation,
            )
        }
        composable(
            route = AppDirections.Error.route
        ) { backStackEntry ->
            val errorMessage = backStackEntry.arguments?.getString("errorMessage").toString()
            ErrorScreen(
                navController = navController,
                errorMessage = errorMessage
            )
        }
    }
}