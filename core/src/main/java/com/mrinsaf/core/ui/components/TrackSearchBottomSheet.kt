package com.mrinsaf.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.R
import com.mrinsaf.core.data.model.TrackUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackSearchBottomSheet(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    searchedTracks: List<TrackUiModel>?,
    onTrackClick: (TrackUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        Column(modifier = modifier
            .fillMaxHeight()
            .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.searchPanelTitle),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            TrackSearchField(
                textFieldValue = textFieldValue,
                onValueChange = onValueChange,
                enabled = true,
                modifier = modifier,
            )

            SearchedTrackList(
                textFieldValue = textFieldValue,
                searchedTracks = searchedTracks,
                onTrackClick = onTrackClick,
                modifier = modifier
            )
        }
    }
}