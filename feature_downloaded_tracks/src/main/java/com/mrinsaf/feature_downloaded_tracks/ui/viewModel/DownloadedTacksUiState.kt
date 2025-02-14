package com.mrinsaf.feature_downloaded_tracks.ui.viewModel

import com.mrinsaf.feature_downloaded_tracks.data.model.Track

data class DownloadedTacksUiState(
    var tracks: List<Track> = emptyList()
)
