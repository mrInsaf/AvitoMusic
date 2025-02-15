package com.mrinsaf.core.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.data.model.TrackUiModel
import com.mrinsaf.core.ui.components.TrackItem

@Composable
fun TracksScreen(
    tracks: List<TrackUiModel>,
    query: String,
    onQueryChange: (String) -> Unit,
    onTrackClick: (TrackUiModel) -> Unit
) {
    Column {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Поиск") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(tracks) { track ->
                TrackItem(
                    albumArtUrl = track.albumArtUrl,
                    title = track.title,
                    artist = track.artist,
                    onTrackClick = { onTrackClick(track) }
                )
            }
        }
    }
}
