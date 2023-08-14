package com.chitneev.graphicdrawer.ui.screens.graph

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chitneev.graphicdrawer.domain.usecase.FetchPointsUseCase
import com.chitneev.graphicdrawer.domain.usecase.SaveGraphToGalleryUseCase
import com.chitneev.graphicdrawer.navigation.Graph
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchPoints: FetchPointsUseCase,
    private val saveGraphToGallery: SaveGraphToGalleryUseCase,
) : ViewModel() {

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
                fetchPoints(countOfPoints)
            }
                .sortedBy { it.x }

            Log.d(logTag, "points: $points")
            _state.value = UiState.Success(points = points)
        }
    }

    init {
        requestPoints()
    }

    fun saveBitmap(bitmap: Bitmap) {
        saveGraphToGallery(bitmap)
    }
}