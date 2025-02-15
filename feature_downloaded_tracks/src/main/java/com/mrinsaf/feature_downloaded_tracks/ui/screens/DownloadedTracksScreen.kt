package com.mrinsaf.feature_downloaded_tracks.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mrinsaf.core.data.model.TrackUiModel
import com.mrinsaf.core.ui.screens.TracksScreen
import com.mrinsaf.feature_downloaded_tracks.R
import com.mrinsaf.feature_downloaded_tracks.ui.viewModel.DownloadedTacksViewModel

@Composable
fun DownloadedTracksScreen(
    navController: NavController,
    trackViewModel: DownloadedTacksViewModel
) {
    val uiState = trackViewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }

    val filteredTracks = uiState.value.tracks
        .map { track ->
            TrackUiModel(
                albumArtUrl = track.albumArtUri.toString(),
                title = track.title,
                artist = track.artist
            )
        }
        .filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.artist.contains(query, ignoreCase = true)
        }

    TracksScreen(
        tracks = filteredTracks,
        title = stringResource(R.string.downloadedScreenTitle),
        query = query,
        onQueryChange = { query = it },
        onTrackClick = { track -> println(track) }
    )
}


