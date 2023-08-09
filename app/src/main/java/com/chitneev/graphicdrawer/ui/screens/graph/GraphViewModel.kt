package com.chitneev.graphicdrawer.ui.screens.graph

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chitneev.graphicdrawer.domain.repository.PointsRepository
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GraphViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val repository: PointsRepository = PointsRepository()

    private val logTag = "GraphViewModel"

    private var countOfPoints: Int = checkNotNull(savedStateHandle["count_point_arg"])

    private var _state = MutableStateFlow(UiState.initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.e(logTag, throwable.message ?: "")
            }
        ) {
            val points = withContext(context = Dispatchers.IO) {
                repository.getPoints(countOfPoints)
            }
            Log.d(logTag, "points: $points")
            _state.value = state.value.copy(points = points)
        }
    }

}