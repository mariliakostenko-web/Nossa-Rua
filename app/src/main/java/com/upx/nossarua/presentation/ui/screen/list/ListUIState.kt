package com.upx.nossarua.presentation.ui.screen.list

import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerDistance
import com.upx.nossarua.domain.model.StreetMarkerType


data class ListUIState(
    val screenTitle: String = "Lista de Incidentes",
    val markers: List<StreetMarkerDistance> = listOf(
        StreetMarkerDistance(
            title = "Test",
            description = "Description",
            position = LatLng(00.00, 00.00),
            type = StreetMarkerType.MISSING_SIGN,

        ),
        StreetMarkerDistance(
            title = "Test2",
            description = "Description",
            position = LatLng(01.29, 02.23),
            type = StreetMarkerType.HOLE
        ),
        StreetMarkerDistance(
            title = "Test2",
            description = "Description",
            position = LatLng(2.29, 1.23),
            type = StreetMarkerType.ANIMALS
        ),
        StreetMarkerDistance(
            title = "Test2",
            description = "Description",
            position = LatLng(4.29, 5.23),
            type = StreetMarkerType.VEGETATION
        ),
    )
)