package com.mrinsaf.feature_api_tracks.data.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mrinsaf.core.data.model.toTrackUiModel
import com.mrinsaf.core.ui.screens.TracksScreen
import com.mrinsaf.feature_api_tracks.R
import com.mrinsaf.feature_api_tracks.data.ui.viewModel.ChartTracksViewModel

@Composable
fun ChartTracksScreen(
    navController: NavController,
    chartViewModel: ChartTracksViewModel
) {
    val uiState = chartViewModel.uiState.collectAsStateWithLifecycle()

    TracksScreen(
        tracks = uiState.value.tracks,
        searchedTracks = uiState.value.searchedTracks,
        query = uiState.value.searchQuery,
        title = stringResource(R.string.chartScreenTitle),
        onQueryChange = { chartViewModel.onQueryChange(it) },
        onTrackClick = { track -> println("track: ") },
    )
}