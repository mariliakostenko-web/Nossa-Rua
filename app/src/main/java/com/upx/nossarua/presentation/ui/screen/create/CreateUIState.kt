package com.upx.nossarua.presentation.ui.screen.create

import com.upx.nossarua.domain.model.StreetMarker


data class CreateUIState(
    val screenTitle: String = "Reportar Incidente",
    val marker: List<StreetMarker> = listOf(),
    val cpf: String = "",
    val message: String = "",
    val isSuccessDialog: Boolean = false,
    val showDialog: Boolean = false
)