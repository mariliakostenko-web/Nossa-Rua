package com.upx.nossarua.data.service

import com.upx.nossarua.data.model.dto.CreateIncidentDTO
import com.upx.nossarua.data.model.dto.CreateIncidentResponseDTO
import com.upx.nossarua.data.model.dto.IncidentDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppService {
    @GET("incidentes")
    suspend fun getData(): Response<List<IncidentDTO>>

    @POST("incidentes/adicionar")
    suspend fun postData(@Body data: CreateIncidentDTO): Response<CreateIncidentResponseDTO>
}