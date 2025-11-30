package com.upx.nossarua.presentation.ui.screen

import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerDistance


data class BaseUIState(
    val marker: MutableList<StreetMarker> = mutableListOf(),
    val markerDistance: List<StreetMarkerDistance> = mutableListOf(),
    val message: String = "",
    val showDialog: Boolean = false
)