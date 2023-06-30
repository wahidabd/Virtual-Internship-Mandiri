package com.wahidabd.mandirinews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.mandirinews.R
import com.wahidabd.mandirinews.presentation.destinations.SearchScreenDestination
import com.wahidabd.mandirinews.ui.theme.ColorSecondary


/**
 * Created by Wahid on 6/29/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun Header(
    navigator: DestinationsNavigator,
    isParent: Boolean = false,
    title: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (!isParent) Arrangement.SpaceBetween else Arrangement.Start
    ) {
        if (isParent) {
            IconButton(
                onClick = { navigator.navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    tint = ColorSecondary,
                    contentDescription = null
                )
            }
        }

        Text(
            text = title,
            color = ColorSecondary,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(if (!isParent) 12.dp else 0.dp)
        )

        if (!isParent) {
            IconButton(onClick = {
                navigator.navigate(SearchScreenDestination())
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = ColorSecondary,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun HeaderHomePreview() {
    Header(
        navigator = EmptyDestinationsNavigator,
        isParent = true,
        title = "Mandiri news",
    )
}