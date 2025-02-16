package com.mrinsaf.feature_api_tracks.data.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mrinsaf.core.ui.screens.TracksScreen
import com.mrinsaf.core.ui.viewModel.CoreViewModel
import com.mrinsaf.feature_api_tracks.R
import com.mrinsaf.feature_api_tracks.data.ui.viewModel.ChartTracksViewModel

@Composable
fun ChartTracksScreen(
    navController: NavController,
    coreViewModel: CoreViewModel,
    chartViewModel: ChartTracksViewModel
) {
    val chartUiState = chartViewModel.uiState.collectAsStateWithLifecycle()
    val coreUiState = coreViewModel.uiState.collectAsStateWithLifecycle()

    TracksScreen(
        tracks = chartUiState.value.tracks,
        searchedTracks = chartUiState.value.searchedTracks,
        query = chartUiState.value.searchQuery,
        title = stringResource(R.string.chartScreenTitle),
        onQueryChange = { chartViewModel.onQueryChange(it) },
        onTrackClick = { track -> println("track: ") },
        isBottomSheetOpened = coreUiState.value.isBottomSheetOpened,
        onBottomSheetClose = {
            coreViewModel.closeSearchBottomSheet()
            chartViewModel.clearSearchQuery()
            chartViewModel.clearSearchedTracks()
        },
        onBottomSheetOpen = { coreViewModel.openSearchBottomSheet() },
    )
}