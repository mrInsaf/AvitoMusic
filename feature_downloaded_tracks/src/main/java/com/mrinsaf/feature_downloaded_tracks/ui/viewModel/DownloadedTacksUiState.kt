package com.mrinsaf.feature_downloaded_tracks.ui.viewModel

import com.mrinsaf.core.data.model.TrackUiModel
import com.mrinsaf.feature_downloaded_tracks.data.model.Track

data class DownloadedTacksUiState(
    var localTracks: List<TrackUiModel> = emptyList(),
    var searchQuery: String = "",
    var filteredTracks: List<TrackUiModel> = emptyList()
)
