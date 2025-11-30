package com.upx.nossarua.presentation.ui.screen.create

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.data.model.dto.CreateIncidentDTO
import com.upx.nossarua.data.model.response.CreateResultResponse
import com.upx.nossarua.data.repository.AppRepository
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

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
        viewModelScope.launch {
            val marker = uiState.value.marker.firstOrNull()
            if (marker == null) {
                Log.d("CreateViewModel", "No marker found.")
                return@launch
            }

            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                appRepository.sendData(
                    data = CreateIncidentDTO(
                        data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        titulo = marker.title,
                        lat = marker.position.latitude.toString(),
                        lng = marker.position.longitude.toString(),
                        categoria = marker.type.typeName,
                        observacao = marker.description
                    )
                )
            } else {
                Log.d("CreateViewModel", "VERSION CODE ERROR")
            }

            Log.d("CreateViewModel", "Result: $result")

            when (result) {
                is CreateResultResponse.Error -> {
                    Log.d("CreateViewModel", "Error: ${result.exception.message}")
                    _uiState.update { state ->
                        state.copy(
                            message = result.exception.message ?: "Erro desconhecido! Tente novamente mais tarde",
                            isSuccessDialog = false,
                            showDialog = true
                        )
                    }
                }

                is CreateResultResponse.Success -> {
                    Log.d("CreateViewModel", "Success: ${result.message}")
                    _uiState.update { state ->
                        state.copy(
                            message = result.message,
                            isSuccessDialog = true,
                            showDialog = true
                        )
                    }
                }
            }
        }
    }

}