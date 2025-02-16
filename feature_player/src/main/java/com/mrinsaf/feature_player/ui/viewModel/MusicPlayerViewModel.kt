package com.mrinsaf.feature_player.ui.viewModel

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.mrinsaf.core.data.repository.network.DeezerNetworkRepository
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
class MusicPlayerViewModel @OptIn(UnstableApi::class)
@Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {

    private val exoPlayer = ExoPlayer.Builder(context).build()

    private val _uiState = MutableStateFlow(MusicPlayerUiState())
    val uiState: StateFlow<MusicPlayerUiState> = _uiState.asStateFlow()

    init {
        exoPlayer.addListener(object : Player.Listener {
            @Deprecated("Deprecated in Java")
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                // Проверяем, готов ли плеер (состояние "готов к воспроизведению")
                if (playbackState == Player.STATE_READY && playWhenReady) {
                    println("Player is ready.")

                    // Обновляем состояние UI только когда плеер готов
                    _uiState.update {
                        it.copy(isPlaying = true, trackDuration = exoPlayer.duration.coerceAtLeast(1L))
                    }

                    // Запускаем обновление прогресса
                    startProgressUpdates()
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

    @OptIn(UnstableApi::class)
    fun playTrack() {
        val currentState = _uiState.value
        val mediaItem = MediaItem.fromUri(currentState.trackUrl ?: return)

        // Устанавливаем медиа и готовим плеер
        exoPlayer.setMediaItem(mediaItem)

        // Подготовка плеера
        exoPlayer.prepare()

        exoPlayer.play()
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            println("paused")
        } else {
            exoPlayer.play()
        }

        // Обновляем состояние UI в любом случае
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
