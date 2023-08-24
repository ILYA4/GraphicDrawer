package com.chitneev.graphicdrawer.ui.screens.common.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme


@Composable
fun LoadingState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingStatePreview() {
    GraphicDrawerTheme {
        LoadingState()
    }
}