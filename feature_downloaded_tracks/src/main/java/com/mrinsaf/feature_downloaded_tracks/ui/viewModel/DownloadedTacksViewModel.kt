package com.mrinsaf.feature_downloaded_tracks.ui.viewModel

import androidx.lifecycle.ViewModel
import com.mrinsaf.feature_downloaded_tracks.data.repository.DeezerLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DownloadedTacksViewModel @Inject constructor(
    private val deezerLocalRepository: DeezerLocalRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(DownloadedTacksUiState())
    val uiState: StateFlow<DownloadedTacksUiState>
        get() = _uiState

    init {
        loadTracks()
    }

    private fun loadTracks() {
        val localTracks = deezerLocalRepository.getDownloadedTracks()
        println("localTracks: $localTracks")
        _uiState.update {
            it.copy(
                tracks = localTracks
            )
        }
    }
}