package com.upx.nossarua.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upx.nossarua.presentation.ui.screen.map.MapScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppDirections.Map.route
    ) {
        composable(
            route = AppDirections.Map.route
        ) { navBackStackEntry ->
            MapScreen(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        }
    }
}