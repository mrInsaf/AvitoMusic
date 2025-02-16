package com.mrinsaf.feature_player.ui.viewModel

data class MusicPlayerUiState(
    val trackUrl: String = "",
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val trackDuration: Long = 0L,
    val trackTitle: String = "",
    val artistName: String = "",
    val albumCoverUrl: String? = null
)