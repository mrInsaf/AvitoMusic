package com.mrinsaf.feature_downloaded_tracks.data.repository

import com.mrinsaf.core.data.repository.DeezerRepository
import com.mrinsaf.feature_downloaded_tracks.data.model.Track

interface DeezerLocalRepository: DeezerRepository {

    fun getDownloadedTracks(): List<Track>

}