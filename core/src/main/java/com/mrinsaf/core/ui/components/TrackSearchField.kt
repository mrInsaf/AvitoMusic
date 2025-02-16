package com.mrinsaf.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.R
import com.mrinsaf.core.data.model.TrackUiModel


@Composable
fun TrackSearchField(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    searchedTracks: List<TrackUiModel>?,
    onTrackClick: (TrackUiModel) -> Unit,
    onFocusChanged: () -> Unit,
    modifier: Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(12.dp)
            )
    ){
        if (!searchedTracks.isNullOrEmpty() && textFieldValue.isNotEmpty()){
            Text(
                text = stringResource(R.string.searchResultsTitle),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(Modifier.size(8.dp))
            LazyColumn(
                modifier = modifier
                    .weight(1f)
//                    .border(1.dp, Color.Green)
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
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    onFocusChanged()
                }
//                .imePadding()
        )
    }
}

@Preview
@Composable
fun TrackSearchFieldPreview() {
    TrackSearchField(
        textFieldValue = "some text",
        onValueChange = {},
        searchedTracks = emptyList<TrackUiModel>(),
        onTrackClick = {},
        modifier = Modifier,
        onFocusChanged = {  }
    )
}