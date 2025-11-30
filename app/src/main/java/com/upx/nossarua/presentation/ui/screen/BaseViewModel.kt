package com.upx.nossarua.presentation.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.data.model.response.FetchResultResponse
import com.upx.nossarua.data.repository.AppRepository
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerDistance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUIState())
    val uiState: StateFlow<BaseUIState> = _uiState

    fun retrieveMarkers() {
        viewModelScope.launch {
            val result = appRepository.fetchData()
            when (result) {
                is FetchResultResponse.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            showDialog = true,
                            message = "${result.exception.message}. Tente novamente"
                        )
                    }
                }
                is FetchResultResponse.Success -> {
                    val data = result.markers.toMutableList()
                    _uiState.update { state ->
                        state.copy(
                            marker = data
                        )
                    }
                }
            }
        }
    }

    fun onCloseDialog() {
        retrieveMarkers()
        _uiState.update { state ->
            state.copy(
                showDialog = false, message = ""
            )
        }
    }

    fun saveLocation(
        userLocation: LatLng,
    ) {
        val markers = uiState.value.marker
        val incidentsDistance = markers.map { incident ->
            StreetMarkerDistance(
                title = incident.title,
                description = incident.description,
                position = incident.position,
                type = incident.type,
                distance = distance(
                    lat_a = incident.position.latitude,
                    lng_a = incident.position.longitude,
                    lat_b = userLocation.latitude,
                    lng_b = userLocation.longitude
                )
            )
        }
        incidentsDistance.sortedBy { it.distance }
        _uiState.update { state ->
            state.copy(
                markerDistance = incidentsDistance
            )
        }
    }

    fun distance(lat_a: Double, lng_a: Double, lat_b: Double, lng_b: Double): Double {
        val earthRadius = 3958.75
        val latDiff = Math.toRadians((lat_b - lat_a))
        val lngDiff = Math.toRadians((lng_b - lng_a))
        val a = sin(latDiff / 2) * sin(latDiff / 2) +
                cos(Math.toRadians(lat_a)) * cos(Math.toRadians(lat_b)) * sin(
            lngDiff / 2
        ) * sin(lngDiff / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val distance = earthRadius * c

        val meterConversion = 1609

        return (distance * meterConversion)/1000
    }
}