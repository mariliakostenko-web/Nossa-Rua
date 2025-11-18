package com.upx.nossarua.presentation.ui.screen.create

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CreateUIState())
    val uiState: StateFlow<CreateUIState> = _uiState

    fun putMarker(latLng: LatLng) {
        val marker = uiState.value.marker.firstOrNull()
        _uiState.update { state ->
            state.copy(
                marker = listOf(
                    StreetMarker(
                        title = marker?.title ?: "",
                        description = marker?.description ?: "",
                        position = latLng,
                        type = marker?.type ?: StreetMarkerType.HOLE
                    )
                )
            )
        }
    }

    fun updateDescription(description: String) {
        _uiState.update { state ->
            state.copy(
                marker = listOf(
                    state.marker.first().copy(
                        description = description
                    )
                )
            )
        }
    }

    fun updateTitle(title: String) {
        _uiState.update { state ->
            state.copy(
                marker = listOf(
                    state.marker.first().copy(
                        title = title
                    )
                )
            )
        }
    }

    fun updateType(type: StreetMarkerType) {
        _uiState.update { state ->
            state.copy(
                marker = listOf(
                    state.marker.first().copy(
                        type = type
                    )
                )
            )
        }
    }

    fun updateCPF(cpf: String) {
        _uiState.update { state ->
            state.copy(
                cpf = cpf
            )
        }
    }

    fun validateCpf(): Boolean {
        val regex = Regex("""^\d{3}\.\d{3}\.\d{3}-\d{2}$|^\d{11}$""")
        return regex.matches(uiState.value.cpf)
    }

    fun onCreateClicked() {
        uiState.value.marker.firstOrNull()?.let { marker ->
            if (marker.description.isEmpty() || marker.title.isEmpty() || uiState.value.cpf.isEmpty()) {
                _uiState.update { state ->
                    state.copy(
                        message = "Preencha todos os dados para reportar!",
                        isSuccessDialog = false,
                        showDialog = true
                    )
                }
            } else if (validateCpf()) {
                addIncident()
            } else {
                _uiState.update { state ->
                    state.copy(
                        message = "CPF inválido!",
                        isSuccessDialog = false,
                        showDialog = true
                    )
                }
            }
        }
    }

    fun onCloseDialog() {
        _uiState.update { state ->
            if (state.isSuccessDialog) {
                CreateUIState()
            } else {
                state.copy(
                    showDialog = false, message = ""
                )
            }
        }
    }

    fun addIncident() {
        _uiState.update { state ->
            state.copy(
                message = "Incidente reportado com sucesso!",
                isSuccessDialog = true,
                showDialog = true
            )
        }
    }
}