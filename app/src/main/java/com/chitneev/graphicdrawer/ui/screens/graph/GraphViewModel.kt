package com.chitneev.graphicdrawer.ui.screens.graph

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chitneev.graphicdrawer.domain.usecase.GetCachedPointsUseCase
import com.chitneev.graphicdrawer.helpers.SaveGraphResult
import com.chitneev.graphicdrawer.helpers.SaveImageToGalleryHelper
import com.chitneev.graphicdrawer.ui.screens.graph.state.Event
import com.chitneev.graphicdrawer.ui.screens.graph.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(
    private val saveGraphToGallery: SaveImageToGalleryHelper,
    private val getCachedPoints: GetCachedPointsUseCase,
) : ViewModel() {

    private val logTag = this.javaClass.name

    private var _state = MutableStateFlow<UiState>(UiState.initial)
    val state = _state.asStateFlow()

    private var _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

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
        val localDateTime = LocalDateTime.now()
        val fileName = "graph$localDateTime.png"

        val result = saveGraphToGallery(bitmap, fileName)
        viewModelScope.launch {
            when(result) {
                is SaveGraphResult.Success -> {
                    _events.emit(Event.ShowSuccessSavingGraphToast(fileName = result.fileName))
                }
                is SaveGraphResult.Error -> {
                    _events.emit(Event.ShowErrorSavingGraphToast(errorMessage = result.errorMessage))
                }
            }
        }
    }
}