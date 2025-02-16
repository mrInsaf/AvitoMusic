package com.mrinsaf.core.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrinsaf.core.data.model.ApiTrack
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val deezerRepository: DeezerNetworkRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoreUiState())
    val uiState: StateFlow<CoreUiState> = _uiState.asStateFlow()

    fun openSearchBottomSheet() {
        _uiState.update {
            it.copy(isBottomSheetOpened = true)
        }
    }


    fun closeSearchBottomSheet() {
        _uiState.update {
            it.copy(isBottomSheetOpened = false)
        }
    }

    suspend fun getTrack(trackId: Long): ApiTrack? {
        return withContext(Dispatchers.IO) {
            try {
                async { deezerRepository.getTrack(trackId) }.await()
            } catch (e: Exception) {
                println("Ошибка при загрузке трека: ${e.message}")
                null
            }
        }
    }

}