package com.upx.nossarua.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.upx.nossarua.presentation.navigation.AppDirections

enum class BottomBarItems(
    val icon: ImageVector,
    val itemName: String,
    val directions: AppDirections
){
    MAP(
        icon = Icons.Filled.Home,
        itemName = "Mapa",
        directions = AppDirections.Map

    ),
    ADD_INCIDENT(
        icon = Icons.Filled.Add,
        itemName = "Adicionar",
        directions = AppDirections.AddIncident
    )
}