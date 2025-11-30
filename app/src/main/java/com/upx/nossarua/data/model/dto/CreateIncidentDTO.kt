package com.upx.nossarua.data.model.dto

data class CreateIncidentDTO(
    val data: String,
    val titulo: String,
    val lat: String,
    val lng: String,
    val categoria: String,
    val observacao: String
)