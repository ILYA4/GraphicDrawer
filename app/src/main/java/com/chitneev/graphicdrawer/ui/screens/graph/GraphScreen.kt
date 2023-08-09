package com.chitneev.graphicdrawer.ui.screens.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState

@Composable
fun GraphScreen() {
    val vm: GraphViewModel = viewModel(factory = SavedStateViewModelFactory() )

    val state = vm.state.collectAsState(initial = UiState.initial)
    val points = state.value.points

    Content(points = points)
}

@Composable
private fun Content(points: List<Point>) {
    Text(text = points.toString(), modifier = Modifier.fillMaxSize())
}