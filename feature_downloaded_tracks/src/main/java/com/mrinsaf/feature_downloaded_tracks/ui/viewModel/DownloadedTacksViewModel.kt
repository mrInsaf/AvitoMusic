package com.mrinsaf.feature_downloaded_tracks.ui.viewModel

import androidx.lifecycle.ViewModel
import com.mrinsaf.core.data.model.TrackUiModel
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
        println("в локальных треках")
        loadTracks()
    }

    private fun loadTracks() {
        val tracks = deezerLocalRepository.getDownloadedTracks()

        val localTracks = tracks.map { track ->
            TrackUiModel(
                albumArtUrl = track.albumArtUri.toString(),
                title = track.title,
                artist = track.artist
            )
        }

        _uiState.update {
            it.copy(
                localTracks = localTracks
            )
        }
    }

    fun clearSearchQuery() {
        _uiState.update {
            it.copy(searchQuery = "")
        }
    }

    fun onQueryChange(newQuery: String) {
        val filteredTracks = uiState.value.localTracks
            .filter {
                it.title.contains(newQuery, ignoreCase = true) ||
                        it.artist.contains(newQuery, ignoreCase = true)
            }
        println("filteredTracks: $filteredTracks")

        _uiState.update {
            it.copy(
                searchQuery = newQuery,
                filteredTracks = filteredTracks
            )
        }
    }
}