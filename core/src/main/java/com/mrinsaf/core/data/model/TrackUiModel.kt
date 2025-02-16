package com.mrinsaf.core.data.model

import android.net.Uri

data class TrackUiModel(
    val trackId: Long,
    val albumArtUrl: String,
    val title: String,
    val artist: String,
    val trackUri: Uri?
)