package com.mrinsaf.core.ui.viewModel

import androidx.lifecycle.ViewModel
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
}