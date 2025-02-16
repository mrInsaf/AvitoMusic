package com.mrinsaf.core.data.repository.network

import com.mrinsaf.core.data.model.ApiTrack
import com.mrinsaf.core.data.network.DeezerApi


class DeezerNetworkRepositoryImpl(private val deezerApi: DeezerApi) : DeezerNetworkRepository {

    override suspend fun getChart(): List<ApiTrack> {
        val chartResponse = deezerApi.getChart()

        if (chartResponse.isSuccessful) {
            return chartResponse.body()?.tracks?.data ?: emptyList()
        } else {
            println(chartResponse.message())
            throw Exception("Ошибка при получении чарта: ${chartResponse.code()}")
        }
    }

    override suspend fun searchTracks(query: String): List<ApiTrack> {
        val searchResponse = deezerApi.search(query)
        println(searchResponse.body())

        if (searchResponse.isSuccessful) {
            return searchResponse.body()?.data ?: emptyList()
        } else {
            throw Exception("Ошибка при поиске треков: ${searchResponse.code()}")
        }
    }

    override suspend fun getTrack(trackId: Long): ApiTrack {
        val response = deezerApi.getTrack(trackId)
        if (response.isSuccessful) {
            return response.body()!!
        }
        else {
            throw Exception("Ошибка при поиске трека: ${response.code()}")
        }

    }

}
