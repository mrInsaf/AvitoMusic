package com.mrinsaf.feature_api_tracks.data.ui.viewModel

import com.mrinsaf.core.data.model.ApiTrack

data class ChartUiState(
    val tracks: List<ApiTrack> = emptyList()
)