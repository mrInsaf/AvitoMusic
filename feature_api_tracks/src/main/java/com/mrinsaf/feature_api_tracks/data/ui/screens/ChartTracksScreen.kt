package com.mrinsaf.feature_api_tracks.data.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mrinsaf.core.ui.screens.TracksScreen
import com.mrinsaf.core.ui.viewModel.CoreViewModel
import com.mrinsaf.feature_api_tracks.R
import com.mrinsaf.feature_api_tracks.data.ui.viewModel.ChartTracksViewModel
import com.mrinsaf.feature_player.ui.viewModel.MusicPlayerViewModel
import kotlinx.coroutines.launch

@Composable
fun ChartTracksScreen(
    navController: NavController,
    coreViewModel: CoreViewModel,
    chartViewModel: ChartTracksViewModel,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    val chartUiState = chartViewModel.uiState.collectAsStateWithLifecycle()
    val coreUiState = coreViewModel.uiState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    TracksScreen(
        tracks = chartUiState.value.tracks,
        searchedTracks = chartUiState.value.searchedTracks,
        query = chartUiState.value.searchQuery,
        title = stringResource(R.string.chartScreenTitle),
        onQueryChange = { chartViewModel.onQueryChange(it) },
        onTrackClick = { trackItem ->
            coroutineScope.launch {
                val track = coreViewModel.getTrack(trackItem.trackId)
                track?.let {
                    musicPlayerViewModel.setCurrentTrack(
                        url = it.preview,
                        title = it.title,
                        artist = it.artist.name,
                        coverUrl = it.album.cover_big
                    )
                }
                navController.navigate("player")
            }
        },
        isBottomSheetOpened = coreUiState.value.isBottomSheetOpened,
        onBottomSheetClose = {
            coreViewModel.closeSearchBottomSheet()
            chartViewModel.clearSearchQuery()
            chartViewModel.clearSearchedTracks()
        },
        onBottomSheetOpen = { coreViewModel.openSearchBottomSheet() },
    )
}