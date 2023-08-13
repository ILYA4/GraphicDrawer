package com.chitneev.graphicdrawer.ui.screens.graph

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chitneev.graphicdrawer.domain.usecase.FetchPointsUseCase
import com.chitneev.graphicdrawer.navigation.Graph
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

    private val fetchPointsUseCase = FetchPointsUseCase()

    private val logTag = this.javaClass.name

    private var countOfPoints: Int = checkNotNull(savedStateHandle[Graph.countPointTypeArg])

    private var _state = MutableStateFlow<UiState>(UiState.initial)
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(logTag, throwable.message ?: "")
        _state.value = UiState.Error(throwable.message ?: "")
    }

    fun requestPoints() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = UiState.Loading
            val points = withContext(context = Dispatchers.IO) {
                fetchPointsUseCase(countOfPoints)
            }
                .sortedBy { it.x }
            Log.d(logTag, "points: $points")
            _state.value = UiState.Success(points = points)
        }
    }

    init {
        requestPoints()
    }

}