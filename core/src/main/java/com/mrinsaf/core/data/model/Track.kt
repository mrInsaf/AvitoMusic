package com.mrinsaf.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: Long,
    val title: String,
    @SerialName("title_short")
    val titleShort: String,
    @SerialName("title_version")
    val titleVersion: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerialName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerialName("explicit_content_cover")
    val explicitContentCover: Int,
    val preview: String,
    @SerialName("md5_image")
    val md5Image: String,
    val position: Int,
    val artist: Artist,
    val album: Album,
    val type: String
)

@Serializable
data class TracksData(
    @SerialName("data")
    val tracks: List<Track>
)