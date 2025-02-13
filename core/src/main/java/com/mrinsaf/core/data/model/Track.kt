package com.mrinsaf.core.data.model

data class Track(
    val id: Long,
    val title: String,
    val titleShort: String,
    val titleVersion: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    val explicitLyrics: Boolean,
    val preview: String,
    val md5Image: String,
    val position: Int,
    val artist: Artist,
    val album: Album,
    val type: String
)

data class TracksData(
    val data: List<Track>
)