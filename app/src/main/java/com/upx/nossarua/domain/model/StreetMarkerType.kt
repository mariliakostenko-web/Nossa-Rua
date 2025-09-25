package com.upx.nossarua.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

enum class StreetMarkerType(
    val typeName: String,
    val icon: ImageVector
) {
    HOLE(
        typeName = "Buraco",
        icon = Icons.Filled.Clear
    ),
    MISSING_SIGN(
        typeName = "Sem Placa",
        icon = Icons.Filled.Warning
    ),
    VEGETATION(
        typeName = "Vegetação na Via",
        icon = Icons.Filled.Delete
    ),
    ANIMALS(
        typeName = "Animais na Via",
        icon = Icons.Filled.Notifications
    )
}