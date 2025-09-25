package com.upx.nossarua.domain.model

import com.google.android.gms.maps.model.LatLng

data class StreetMarker(
    val id: Int,
    val title: String,
    val description: String,
    val position: LatLng,
    val type: StreetMarkerType
)