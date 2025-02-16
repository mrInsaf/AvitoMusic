package com.mrinsaf.core.data.network

import com.mrinsaf.core.data.model.ApiTrack
import com.mrinsaf.core.data.model.ChartResponse
import com.mrinsaf.core.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApi {
    @GET("chart")
    suspend fun getChart(): Response<ChartResponse>

    @GET("search")
    suspend fun search(@Query("q") query: String): Response<SearchResponse>

    @GET("track/{id}")
    suspend fun getTrack(@Path("id") trackId: Long): Response<ApiTrack>
}
