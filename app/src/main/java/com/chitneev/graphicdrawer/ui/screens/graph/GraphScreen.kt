package com.chitneev.graphicdrawer.ui.screens.graph

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.screens.graph.state.ErrorState
import com.chitneev.graphicdrawer.ui.screens.common.state.LoadingState
import com.chitneev.graphicdrawer.ui.screens.graph.state.Event
import com.chitneev.graphicdrawer.ui.screens.graph.state.SuccessState
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GraphScreen() {
    val vm: GraphViewModel = hiltViewModel()

    val state = vm.state.collectAsState(initial = UiState.initial).value

    val context = LocalContext.current

    LaunchedEffect(key1 = vm)  {
        vm.events.collectLatest {
            when(it) {
                is Event.ShowErrorSavingGraphToast -> {
                    Toast
                        .makeText(context, context.getString(R.string.file_not_saved_error, it.errorMessage), Toast.LENGTH_SHORT)
                        .show()
                }
                is Event.ShowSuccessSavingGraphToast -> {
                    Toast
                        .makeText(context, context.getString(R.string.file_saved, it.fileName), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    Content(
       state = state,
       onRetryClick = { vm.requestPoints() },
       onBitmapCreated = {
           vm.saveBitmap(it)
       }
   )
}

@Composable
private fun Content(
    state: UiState,
    onRetryClick: () -> Unit,
    onBitmapCreated: (Bitmap) -> Unit,
) {
    when(state) {
        is UiState.Loading -> LoadingState()
        is UiState.Error -> ErrorState(errorMessage = state.errorText, onRetryClick)
        is UiState.Success -> SuccessState(
            points = state.points,
            onBitmapCreated = onBitmapCreated
        )
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
        Content(state = UiState.Success(points), {}, {})
    }
}

@Composable
@Preview
private fun ErrorPreview() {
    GraphicDrawerTheme {
        Content(state = UiState.Error("Ошибка"), {}, {})
    }
}

@Composable
@Preview
private fun LoadingPreview() {
    GraphicDrawerTheme {
        Content(state = UiState.Loading, {}, {})
    }
}




