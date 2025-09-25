package com.upx.nossarua.data.repository

import com.upx.nossarua.data.datasource.AppDataSource
import javax.inject.Inject

interface AppRepository {}

class AppRepositoryImpl @Inject constructor(
    private val dataSource: AppDataSource
) : AppRepository {
}