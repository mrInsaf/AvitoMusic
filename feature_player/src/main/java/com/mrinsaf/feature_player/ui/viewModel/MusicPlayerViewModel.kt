package com.mrinsaf.feature_player.ui.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    private val exoPlayer = ExoPlayer.Builder(context).build()

    private val _uiState = MutableStateFlow(MusicPlayerUiState())
    val uiState: StateFlow<MusicPlayerUiState> = _uiState.asStateFlow()

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                _uiState.update {
                    it.copy(isPlaying = exoPlayer.isPlaying)
                }
            }
        })
    }

    fun setCurrentTrack(url: String, title: String, artist: String, coverUrl: String?) {
        _uiState.update {
            it.copy(
                trackUrl = url,
                trackTitle = title,
                artistName = artist,
                albumCoverUrl = coverUrl
            )
        }
    }

    fun playTrack() {
        val currentState = _uiState.value
        val mediaItem = MediaItem.fromUri(currentState.trackUrl ?: return)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        _uiState.update { it.copy(isPlaying = true, trackDuration = exoPlayer.duration) }

        startProgressUpdates()
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
        _uiState.update { it.copy(isPlaying = exoPlayer.isPlaying) }
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
        _uiState.update { it.copy(currentPosition = position) }
    }

    private fun startProgressUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(500)
                _uiState.update { it.copy(currentPosition = exoPlayer.currentPosition) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}

data class Track(
    val title: String,
    val artist: String,
    val album: String?,
    val coverUrl: String?,
    val url: String
)
