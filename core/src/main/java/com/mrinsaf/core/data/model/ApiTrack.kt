package com.mrinsaf.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiTrack(
    val id: Long,
    val title: String,
    val artist: Artist,
    val album: Album,
    val duration: Int,
    val link: String,
    val position: Int? = null,
    val preview: String,
)

fun ApiTrack.toTrackUiModel(): TrackUiModel {
    return TrackUiModel(
        albumArtUrl = this.album.cover_big,
        title = this.title,
        artist = this.artist.name,
        trackId = this.id,
        trackUri = null
    )
}