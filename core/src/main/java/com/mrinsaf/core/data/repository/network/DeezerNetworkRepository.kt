package com.mrinsaf.core.data.repository.network

import com.mrinsaf.core.data.model.ApiTrack
import com.mrinsaf.core.data.repository.DeezerRepository

interface DeezerNetworkRepository: DeezerRepository {
    suspend fun getChart(): List<ApiTrack>
    suspend fun searchTracks(query: String): List<ApiTrack>
}