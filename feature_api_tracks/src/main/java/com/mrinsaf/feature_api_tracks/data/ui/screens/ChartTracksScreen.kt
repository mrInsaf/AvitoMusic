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
    var query by remember { mutableStateOf("") }

    TracksScreen(
        tracks = uiState.value.tracks.map { it.toTrackUiModel() },
        title = stringResource(R.string.chartScreenTitle),
        query = query,
        onQueryChange = { println("query change") },
        onTrackClick = { track ->
            println("track: ")
        }
    )
}
