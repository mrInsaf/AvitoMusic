package com.mrinsaf.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val title: String,
    val cover_big: String,
)