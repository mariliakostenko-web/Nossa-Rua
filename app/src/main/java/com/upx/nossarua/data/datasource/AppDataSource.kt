package com.upx.nossarua.data.datasource

import com.upx.nossarua.data.service.AppService
import javax.inject.Inject
import javax.inject.Singleton


interface AppDataSource{

}

@Singleton
class AppDataSourceImpl @Inject constructor(
    private val service: AppService
): AppDataSource {}