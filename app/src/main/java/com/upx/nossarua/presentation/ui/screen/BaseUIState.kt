package com.upx.nossarua.presentation.ui.screen

import com.upx.nossarua.domain.model.StreetMarker


data class BaseUIState(
    val marker: MutableList<StreetMarker> = mutableListOf(),
)