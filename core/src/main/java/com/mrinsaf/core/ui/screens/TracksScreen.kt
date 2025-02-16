package com.mrinsaf.core.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.data.model.TrackUiModel
import com.mrinsaf.core.ui.components.TrackItem
import com.mrinsaf.core.ui.components.TrackSearchField

@Composable
fun TracksScreen(
    tracks: List<TrackUiModel>,
    searchedTracks: List<TrackUiModel>?,
    title: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onTrackClick: (TrackUiModel) -> Unit
) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        val titleColor = if (query.isNotEmpty()) {
             MaterialTheme.colorScheme.tertiary
        }
        else {
            MaterialTheme.colorScheme.onBackground
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = titleColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tracks) { track ->
                TrackItem(
                    albumArtUrl = track.albumArtUrl,
                    title = track.title,
                    artist = track.artist,
                    onTrackClick = { onTrackClick(track) }
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 24.dp
        ) {
            TrackSearchField(
                textFieldValue = query,
                onValueChange = onQueryChange,
                searchedTracks = searchedTracks,
                onTrackClick = { },
                onFocusChanged = { },
                modifier = Modifier
            )
        }
    }
}



