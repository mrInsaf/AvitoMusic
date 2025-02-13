package com.mrinsaf.core.data.model.networkResponses

import com.mrinsaf.core.data.model.Track
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val data: List<Track>
)