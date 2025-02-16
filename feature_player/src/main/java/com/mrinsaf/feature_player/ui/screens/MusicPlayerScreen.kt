package com.mrinsaf.feature_player.ui.screens

import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mrinsaf.feature_player.ui.viewModel.MusicPlayerViewModel


@Composable
fun MusicPlayerScreen(viewModel: MusicPlayerViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect("") {
        viewModel.playTrack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Обложка трека
        AsyncImage(
            model = uiState.albumCoverUrl
                ?: "https://via.placeholder.com/200", // Заглушка
            contentDescription = "Album Cover",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Название трека и исполнитель
        Text(
            text = uiState.trackTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = uiState.artistName,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Прогресс-бар
        Slider(
            value = if (uiState.trackDuration > 0) uiState.currentPosition.toFloat() / uiState.trackDuration else 0f,
            onValueChange = { newValue ->
                val seekPosition = (newValue * uiState.trackDuration).toLong()
                viewModel.seekTo(seekPosition)
            }
        )

        // Тайминги
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatTime(uiState.currentPosition))
            Text(text = formatTime(uiState.trackDuration))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки управления
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* TODO: Добавить логику предыдущего трека */ }) {
                Icon(Icons.Default.Build, contentDescription = "Previous")
            }

            IconButton(onClick = { viewModel.togglePlayPause() }) {
                Icon(
                    if (uiState.isPlaying) Icons.Default.Home else Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause",
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(onClick = { /* TODO: Добавить логику следующего трека */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Next")
            }
        }
    }
}

// Функция форматирования времени в mm:ss
fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
