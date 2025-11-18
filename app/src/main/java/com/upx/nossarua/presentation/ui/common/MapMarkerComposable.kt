package com.upx.nossarua.presentation.ui.common

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.scale
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.upx.nossarua.R
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerType


@Composable
@GoogleMapComposable
fun MapMarkerComposable(
    markers: List<StreetMarker>,
    onMarkerClicked: (Marker) -> Boolean = { false }
) {
    val context = LocalContext.current
    markers.forEach { marker ->
        val pinBitmap = BitmapFactory.decodeResource(
            context.resources,
            when(marker.type){
                StreetMarkerType.HOLE -> R.drawable.person
                StreetMarkerType.MISSING_SIGN -> R.drawable.sign
                StreetMarkerType.VEGETATION -> R.drawable.forest
                StreetMarkerType.ANIMALS -> R.drawable.paw
            }
        )
        val pinBitmapScaled = pinBitmap.scale(64, 64)
        val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(pinBitmapScaled)
        val markerState = rememberUpdatedMarkerState(newPosition = marker.position)
        Marker(
            state = markerState,
            icon = bitmapDescriptor,
            snippet = marker.description,
            tag = marker,
            title = marker.title,
            onClick = { marker ->
                onMarkerClicked(marker)
                false
            }
        )
    }
}

@Composable
private fun rememberUpdatedMarkerState(newPosition: LatLng): MarkerState =
    remember { MarkerState(position = newPosition) }
        .apply { position = newPosition }