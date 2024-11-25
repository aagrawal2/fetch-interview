package com.fetch.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.fetch.core.ui.R

@Composable
fun FetchError(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_large)),
            imageVector = Icons.Filled.Close,
            contentDescription = "Error",
            tint = Color.Red
        )
        Text(
            text = stringResource(R.string.fetch_error),
            color = Color.Red,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
