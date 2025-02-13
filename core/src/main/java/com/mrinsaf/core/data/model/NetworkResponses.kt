package com.mrinsaf.core.data.model
import kotlinx.serialization.Serializable

@Serializable
data class ChartResponse(
    val tracks: Tracks
)

@Serializable
data class Tracks(
    val data: List<Track>
)

@Serializable
data class SearchResponse(
    val data: List<Track>
)