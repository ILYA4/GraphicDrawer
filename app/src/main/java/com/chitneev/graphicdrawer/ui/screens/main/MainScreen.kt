package com.chitneev.graphicdrawer.ui.screens.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.ui.screens.common.state.LoadingState
import com.chitneev.graphicdrawer.ui.screens.main.state.Event
import com.chitneev.graphicdrawer.ui.screens.main.state.InputState
import com.chitneev.graphicdrawer.ui.screens.main.state.UiState
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    onNavigateToGraphScreen: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state = viewModel.uiState.collectAsState(UiState.initial).value
    val events = viewModel.events

    LaunchedEffect(key1 = viewModel)  {
        events.collectLatest {
            when(it) {
                Event.NavigateToGraph -> { onNavigateToGraphScreen() }
                Event.ShowErrorLoadingToast -> {
                    Toast.makeText(context, context.getText(R.string.error_text), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Content(
        state = state,
        onRunClick = { count -> viewModel.onRunClick(count) }
    )
}

@Composable
private fun Content(
    state: UiState,
    onRunClick: (Int) -> Unit
) {

    when (state) {
        UiState.Loading -> LoadingState()
        is UiState.Input -> InputState(
            count = state.count,
            onRunClick = onRunClick
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    GraphicDrawerTheme {
        Content(UiState.initial) {}
    }
}