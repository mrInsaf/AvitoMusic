package com.mrinsaf.core.data.repository.network

import com.mrinsaf.core.data.model.Track
import com.mrinsaf.core.data.repository.DeezerRepository

interface DeezerNetworkRepository: DeezerRepository {
    suspend fun getChart(): List<Track>
    suspend fun searchTracks(query: String): List<Track>
}