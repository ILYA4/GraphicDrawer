package com.chitneev.graphicdrawer.ui.screens.graph.state

import com.chitneev.graphicdrawer.domain.models.Point

sealed interface UiState {

    data class Success(val points: List<Point>) : UiState
    object Loading : UiState
    data class Error(val errorText: String) : UiState
    companion object {
        val initial = Loading
    }
}