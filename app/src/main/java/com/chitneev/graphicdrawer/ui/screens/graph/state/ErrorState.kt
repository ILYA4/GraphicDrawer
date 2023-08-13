package com.chitneev.graphicdrawer.ui.screens.graph.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun ErrorState(
    errorMessage: String,
    onRetryClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.error_text, errorMessage),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(id = R.string.retry_request_button_text))
        }
    }
}

@Preview
@Composable
private fun ErrorStatePreview() {
    GraphicDrawerTheme {
        ErrorState(errorMessage = "Что-то упало") {}
    }
}