package com.mrinsaf.feature_downloaded_tracks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mrinsaf.feature_downloaded_tracks.data.model.Track
import com.mrinsaf.feature_downloaded_tracks.ui.viewModel.DownloadedTacksViewModel

@Composable
fun DownloadedTracksScreen(
    navController: NavController,
    trackViewModel: DownloadedTacksViewModel = hiltViewModel()
) {
    val uiState = trackViewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }

    val filteredTracks = uiState.value.tracks.filter {
        it.title.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true)
    }

    Column {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Поиск") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Список треков
        LazyColumn {
            items(filteredTracks) { track ->
                TrackItem(track = track, onTrackClick = { TODO() })
            }
        }
    }
}

@Composable
fun TrackItem(track: Track, onTrackClick: (Track) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTrackClick(track) }
            .padding(16.dp)
    ) {
        // Обложка
        Image(
            painter = rememberAsyncImagePainter(track.albumArtUri),
            contentDescription = "Cover for ${track.title}",
            modifier = Modifier.size(50.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Название и автор
        Column {
            Text(text = track.title, fontWeight = FontWeight.Bold)
            Text(text = track.artist)
        }
    }
}

