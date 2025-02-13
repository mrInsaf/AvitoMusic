package com.mrinsaf.core.data.repository

import com.mrinsaf.core.data.model.Track

interface DeezerRepository {
    suspend fun getChart(): List<Track>
    suspend fun searchTracks(query: String): List<Track>
}