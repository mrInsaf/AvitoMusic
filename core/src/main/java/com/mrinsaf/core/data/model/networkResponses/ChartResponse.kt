package com.mrinsaf.core.data.model.networkResponses

import com.mrinsaf.core.data.model.TracksData
import kotlinx.serialization.Serializable

@Serializable
data class ChartResponse(
    val tracks: TracksData
)