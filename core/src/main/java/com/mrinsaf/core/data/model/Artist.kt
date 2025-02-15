package com.mrinsaf.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: Int,
    val name: String,
    val picture: String,
)