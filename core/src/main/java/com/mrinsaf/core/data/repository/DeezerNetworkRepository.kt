package com.mrinsaf.core.data.repository

import com.mrinsaf.core.data.model.Track
import com.mrinsaf.core.data.network.DeezerApi


class DeezerNetworkRepository(private val deezerApi: DeezerApi) : DeezerRepository {

    override suspend fun getChart(): List<Track> {
        val chartResponse = deezerApi.getChart()

        if (chartResponse.isSuccessful) {
            return chartResponse.body()?.tracks?.data ?: emptyList()
        } else {
            throw Exception("Ошибка при получении чарта: ${chartResponse.code()}")
        }
    }

    override suspend fun searchTracks(query: String): List<Track> {
        val searchResponse = deezerApi.search(query)

        if (searchResponse.isSuccessful) {
            return searchResponse.body()?.data ?: emptyList()
        } else {
            throw Exception("Ошибка при поиске треков: ${searchResponse.code()}")
        }
    }
}
