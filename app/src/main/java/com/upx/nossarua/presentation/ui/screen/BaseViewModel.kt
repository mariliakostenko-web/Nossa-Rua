package com.upx.nossarua.presentation.ui.screen

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.data.repository.AppRepositoryImpl
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerType
import com.upx.nossarua.presentation.ui.screen.create.CreateUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUIState())
    val uiState: StateFlow<BaseUIState> = _uiState

    fun updateMarkers(marker: StreetMarker) {
        _uiState.update { state ->
            state.copy(marker = (state.marker + marker) as MutableList<StreetMarker>)
        }
    }
}