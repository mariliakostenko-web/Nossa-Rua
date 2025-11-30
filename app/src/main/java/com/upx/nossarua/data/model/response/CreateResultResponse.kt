package com.upx.nossarua.data.model.response

sealed class CreateResultResponse{
    data class Success(val message: String) : CreateResultResponse()
    data class Error(val exception: Exception) : CreateResultResponse()
}