package com.mrinsaf.feature_api_tracks.data.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartTracksViewModel @Inject constructor(
    private val deezerRepository: DeezerNetworkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChartUiState())
    val uiState: StateFlow<ChartUiState> = _uiState.asStateFlow()

    private var lastQueryTime = 0L
    private val throttleDelay = 500L

    init {
        fetchChartTracks()
    }

    private fun fetchChartTracks() {
        viewModelScope.launch {
            try {
                val tracks = deezerRepository.getChart()
                _uiState.value = ChartUiState(tracks)
            } catch (e: Exception) {
                println("Ошибка при загрузке чарта: $e")
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        val currentTime = System.currentTimeMillis()

        _uiState.update {
            it.copy(searchQuery = newQuery)
        }

        if (currentTime - lastQueryTime > throttleDelay) {
            println("uiState.value.searchQuery: ${uiState.value.searchQuery}")
            lastQueryTime = currentTime
            viewModelScope.launch {
                delay(500L)
                val searchQuery = uiState.value.searchQuery
                if (searchQuery.isNotEmpty()) {
                    println("searching for ${uiState.value.searchQuery}")
                }
            }
        }
    }

}
