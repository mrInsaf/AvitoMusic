package com.mrinsaf.core.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.data.model.TrackUiModel
import com.mrinsaf.core.ui.components.TrackItem

@Composable
fun TracksScreen(
    tracks: List<TrackUiModel>,
    title: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onTrackClick: (TrackUiModel) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
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


