package com.mrinsaf.core.data.repository

import androidx.compose.ui.graphics.painter.Painter

interface DeezerRepository {
    fun createPainter(imageSource: String): Painter
}