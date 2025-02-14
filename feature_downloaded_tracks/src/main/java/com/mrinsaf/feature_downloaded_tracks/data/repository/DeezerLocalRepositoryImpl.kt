package com.mrinsaf.feature_downloaded_tracks.data.repository

import androidx.compose.ui.graphics.painter.Painter
import com.mrinsaf.core.data.network.DeezerApi


class DeezerLocalRepositoryImpl(private val deezerApi: DeezerApi) : DeezerLocalRepository {
    override fun createPainter(imageSource: String): Painter {
        TODO("Not yet implemented")
    }

}
