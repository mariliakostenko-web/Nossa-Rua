package com.upx.nossarua.presentation.navigation

sealed class AppDirections(val route: String) {
    data object Map : AppDirections("map")
    data object AddIncident : AppDirections("add_incident")
}