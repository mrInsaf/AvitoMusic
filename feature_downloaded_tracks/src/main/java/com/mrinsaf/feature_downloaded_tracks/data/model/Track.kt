package com.mrinsaf.feature_downloaded_tracks.data.model

import android.net.Uri

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val trackUri: Uri,
    val albumArtUri: Uri
)