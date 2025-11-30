package com.upx.nossarua.data.model.dto


data class IncidentDTO(
    val id: Int,
    val data: String,
    val lat: String,
    val lng: String,
    val titulo: String,
    val categoria: String,
    val observacao: String
)