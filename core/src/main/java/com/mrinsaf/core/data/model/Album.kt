package com.mrinsaf.core.data.model

data class Album(
    val id: Long,
    val title: String,
    val cover: String,
    val coverSmall: String,
    val coverMedium: String,
    val coverBig: String,
    val tracklist: String,
    val type: String
)