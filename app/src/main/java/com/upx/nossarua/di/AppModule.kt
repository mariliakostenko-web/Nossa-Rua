package com.upx.nossarua.di

import com.upx.nossarua.data.datasource.AppDataSource
import com.upx.nossarua.data.datasource.AppDataSourceImpl
import com.upx.nossarua.data.repository.AppRepository
import com.upx.nossarua.data.repository.AppRepositoryImpl
import com.upx.nossarua.data.service.AppService
import com.upx.nossarua.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataSource(service: AppService): AppDataSource {
        return AppDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideRepository(dataSource: AppDataSource): AppRepository {
        return AppRepositoryImpl(dataSource)
    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response data
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}