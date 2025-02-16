package com.mrinsaf.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.R
import com.mrinsaf.core.data.model.TrackUiModel

@Composable
fun SearchedTrackList(
    textFieldValue: String,
    searchedTracks: List<TrackUiModel>?,
    onTrackClick: (TrackUiModel) -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!searchedTracks.isNullOrEmpty() && textFieldValue.isNotEmpty()) {
            println("loaded")
            Spacer(Modifier.size(8.dp))
            LazyColumn(
                modifier = modifier
                    .weight(1f)
            ) {
                items(searchedTracks) { searchedTrack ->
                    TrackItem(
                        albumArtUrl = searchedTrack.albumArtUrl,
                        title = searchedTrack.title,
                        artist = searchedTrack.artist,
                        onTrackClick = { onTrackClick(searchedTrack) }
                    )
                }
            }
        }
        if (searchedTracks.isNullOrEmpty() && textFieldValue.isNotEmpty()) {
            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground,
                trackColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
            )
        }
    }

}