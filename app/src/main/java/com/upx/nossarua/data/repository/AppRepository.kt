package com.upx.nossarua.data.repository

import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.data.model.dto.CreateIncidentDTO
import com.upx.nossarua.data.model.response.CreateResultResponse
import com.upx.nossarua.data.model.response.FetchResultResponse
import com.upx.nossarua.data.service.AppService
import com.upx.nossarua.domain.model.StreetMarker
import com.upx.nossarua.domain.model.StreetMarkerType.ANIMALS
import com.upx.nossarua.domain.model.StreetMarkerType.HOLE
import com.upx.nossarua.domain.model.StreetMarkerType.MISSING_SIGN
import com.upx.nossarua.domain.model.StreetMarkerType.VEGETATION
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appService: AppService
) {
    suspend fun fetchData(): FetchResultResponse{
        return try {
            val response = appService.getData()
            if (response.isSuccessful) {
                response.body()?.let { FetchResultResponse.Success(
                    markers = it.map {
                        StreetMarker(
                            title = it.titulo,
                            description = it.observacao,
                            position = LatLng(it.lat.toDouble(), it.lng.toDouble() ),
                            type = when(it.categoria){
                                HOLE.typeName -> HOLE
                                MISSING_SIGN.typeName -> MISSING_SIGN
                                VEGETATION.typeName -> VEGETATION
                                ANIMALS.typeName -> ANIMALS
                                else -> HOLE
                            }
                        )
                    }
                ) } ?: FetchResultResponse.Error(Exception("Empty response"))
            } else {
                FetchResultResponse.Error(Exception("${response.message()}"))
            }
        } catch (e: Exception) {
            FetchResultResponse.Error(e)
        }
    }

    suspend fun sendData(data: CreateIncidentDTO): CreateResultResponse{
        return try {
            val response = appService.postData(data)
            if (response.isSuccessful) {
                response.body()?.let { CreateResultResponse.Success(it.message) } ?: CreateResultResponse.Error(Exception("Empty response"))
            } else {
                CreateResultResponse.Error(Exception(response.body().toString()))

            }
        } catch (e: Exception) {
            CreateResultResponse.Error(e)

        }
    }
}