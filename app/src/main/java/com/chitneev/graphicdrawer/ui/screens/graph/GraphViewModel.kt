package com.chitneev.graphicdrawer.ui.screens.graph

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.chitneev.graphicdrawer.domain.usecase.GetCachedPointsUseCase
import com.chitneev.graphicdrawer.domain.usecase.SaveGraphToGalleryUseCase
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.IllegalStateException
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(
    private val saveGraphToGallery: SaveGraphToGalleryUseCase,
    private val getCachedPoints: GetCachedPointsUseCase,
) : ViewModel() {

    private val logTag = this.javaClass.name

    private var _state = MutableStateFlow<UiState>(UiState.initial)
    val state = _state.asStateFlow()

    fun requestPoints() {
        _state.value = UiState.Loading
        try {
            val points = getCachedPoints()
            Log.d(logTag, "points: $points")
            _state.value = UiState.Success(points = points)
        } catch (e: IllegalStateException) {
            Log.e(logTag, e.message ?: "")
            _state.value = UiState.Error(e.message ?: "")
        }
    }

    init {
        requestPoints()
    }

    fun saveBitmap(bitmap: Bitmap) {
        saveGraphToGallery(bitmap)
    }
}