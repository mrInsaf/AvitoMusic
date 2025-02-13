package com.mrinsaf.core.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mrinsaf.core.data.repository.DeezerNetworkRepository
import com.mrinsaf.core.data.repository.DeezerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.deezer.com/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideDeezerApi(retrofit: Retrofit): DeezerApi = retrofit.create(DeezerApi::class.java)

    @Provides
    @Singleton
    fun provideDeezerRepository(api: DeezerApi): DeezerRepository = DeezerNetworkRepository(api)
}