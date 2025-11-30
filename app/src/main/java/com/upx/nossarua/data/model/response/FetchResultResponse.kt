package com.upx.nossarua.data.model.response

import com.upx.nossarua.domain.model.StreetMarker

sealed class FetchResultResponse{
    data class Success(val markers: List<StreetMarker>) : FetchResultResponse()
    data class Error(val exception: Exception) : FetchResultResponse()
}