package com.upx.nossarua.presentation.navigation


sealed class AppDirections(val route: String) {
    data object Map : AppDirections("map")
    data object Create : AppDirections("create")
    data object List : AppDirections("list")
    data object Error : AppDirections("error/{errorMessage}")
}