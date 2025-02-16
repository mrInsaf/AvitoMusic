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
import com.mrinsaf.core.ui.viewModel.CoreViewModel
import com.mrinsaf.feature_downloaded_tracks.R
import com.mrinsaf.feature_downloaded_tracks.ui.viewModel.DownloadedTacksViewModel
import com.mrinsaf.feature_player.ui.viewModel.MusicPlayerViewModel

@Composable
fun DownloadedTracksScreen(
    navController: NavController,
    coreViewModel: CoreViewModel,
    downloadedTracksViewModel: DownloadedTacksViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
) {
    val downloadedTracksUiState = downloadedTracksViewModel.uiState.collectAsStateWithLifecycle()
    val coreUiState = coreViewModel.uiState.collectAsStateWithLifecycle()

    TracksScreen(
        tracks = downloadedTracksUiState.value.localTracks,
        title = stringResource(R.string.downloadedScreenTitle),
        query = downloadedTracksUiState.value.searchQuery,
        onQueryChange = { downloadedTracksViewModel.onQueryChange(it) },
        onTrackClick = {
            musicPlayerViewModel.setCurrentTrack(
                url = it.trackUri.toString(),
                title = it.title,
                artist = it.artist,
                coverUrl = it.albumArtUrl
            )
            navController.navigate("player")
        },
        searchedTracks = downloadedTracksUiState.value.filteredTracks,
        isBottomSheetOpened = coreUiState.value.isBottomSheetOpened,
        onBottomSheetClose = {
            coreViewModel.closeSearchBottomSheet()
            downloadedTracksViewModel.clearSearchQuery()
         },
        onBottomSheetOpen = { coreViewModel.openSearchBottomSheet() },
    )
}


