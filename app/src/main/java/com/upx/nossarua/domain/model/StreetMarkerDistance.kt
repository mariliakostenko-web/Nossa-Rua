package com.upx.nossarua.domain.model

import com.google.android.gms.maps.model.LatLng

data class StreetMarkerDistance(
    val title: String,
    val description: String,
    val position: LatLng,
    val type: StreetMarkerType,
    val distance: Double = 0.0
)