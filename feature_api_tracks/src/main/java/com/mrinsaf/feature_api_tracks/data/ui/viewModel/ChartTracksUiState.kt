package com.mrinsaf.feature_api_tracks.data.ui.viewModel

import com.mrinsaf.core.data.model.TrackUiModel

data class ChartUiState(
    val tracks: List<TrackUiModel> = emptyList(),
    var searchQuery: String = "",

    val searchedTracks: List<TrackUiModel> = emptyList(),
)