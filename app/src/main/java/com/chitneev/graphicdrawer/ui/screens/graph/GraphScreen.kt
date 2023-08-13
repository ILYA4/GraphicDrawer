package com.chitneev.graphicdrawer.ui.screens.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.screens.graph.state.ErrorState
import com.chitneev.graphicdrawer.ui.screens.graph.state.LoadingState
import com.chitneev.graphicdrawer.ui.screens.graph.state.SuccessState
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun GraphScreen() {
    val vm: GraphViewModel = viewModel(factory = SavedStateViewModelFactory())

    val state = vm.state.collectAsState(initial = UiState.initial).value

   Content(state = state) {
       vm.requestPoints()
   }
}

@Composable
private fun Content(
    state: UiState,
    onRetryClick: () -> Unit,
) {
    when(state) {
        is UiState.Loading -> LoadingState()
        is UiState.Error -> ErrorState(errorMessage = state.errorText, onRetryClick)
        is UiState.Success -> SuccessState(points = state.points)
    }
}

@Composable
@Preview
private fun SuccessPreview() {
    val points = listOf(
        Point(1.0,2.0),
        Point(2.0,1.0),
        Point(4.0,3.0),
        Point(5.0,15.0),
        Point(6.0, 22.04),
    )
    GraphicDrawerTheme {
        Content(state = UiState.Success(points)) { }
    }
}

@Composable
@Preview
private fun ErrorPreview() {
    GraphicDrawerTheme {
        Content(state = UiState.Error("Ошибка")) { }
    }
}

@Composable
@Preview
private fun LoadingPreview() {
    GraphicDrawerTheme {
        Content(state = UiState.Loading) { }
    }
}




