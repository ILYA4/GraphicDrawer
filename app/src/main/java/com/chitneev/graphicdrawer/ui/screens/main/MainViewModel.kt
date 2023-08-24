package com.chitneev.graphicdrawer.ui.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chitneev.graphicdrawer.domain.usecase.FetchPointsUseCase
import com.chitneev.graphicdrawer.ui.screens.main.state.Event
import com.chitneev.graphicdrawer.ui.screens.main.state.State
import com.chitneev.graphicdrawer.ui.screens.main.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchPoints: FetchPointsUseCase
): ViewModel() {

    private val logTag = this.javaClass.name

    private var state = State("")

    private var _uiState = MutableStateFlow<UiState>(UiState.initial)
    val uiState = _uiState.asStateFlow()


    private var _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(logTag, throwable.message ?: "")
        viewModelScope.launch {
            _events.emit(Event.ShowErrorLoadingToast)
            _uiState.emit(UiState.Input(state.inputText))
        }
    }
    fun onRunClick(count: Int) {
        viewModelScope.launch(exceptionHandler) {
            state = state.copy(inputText = count.toString())
            _uiState.emit(UiState.Loading)

            fetchPoints(count)

            _events.emit(Event.NavigateToGraph)
            _uiState.emit(UiState.Input(state.inputText))
        }
    }
}