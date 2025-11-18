package com.upx.nossarua.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.upx.nossarua.presentation.navigation.AppDirections
import kotlinx.serialization.Serializable

@Serializable
enum class BottomBarItems(
    val icon: ImageVector,
    val itemName: String,
) {
    MAP(
        icon = Icons.Filled.Home,
        itemName = "Mapa",
        ),
    CREATE(
        icon = Icons.Filled.Add,
        itemName = "Reportar",
    )
}