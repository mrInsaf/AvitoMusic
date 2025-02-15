package com.mrinsaf.core.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mrinsaf.core.data.repository.DeezerRepository
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepository
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true }
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .cache(null)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.deezer.com/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()


    @Provides
    @Singleton
    fun provideDeezerApi(retrofit: Retrofit): DeezerApi = retrofit.create(DeezerApi::class.java)

    @Provides
    @Singleton
    fun provideDeezerNetworkRepository(api: DeezerApi): DeezerNetworkRepository = DeezerNetworkRepositoryImpl(api)
}