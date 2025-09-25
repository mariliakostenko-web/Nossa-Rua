package com.upx.nossarua.presentation.ui.screen.map

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MapUIState())
    val uiState: StateFlow<MapUIState> = _uiState


}