package com.chitneev.graphicdrawer.ui.screens.main.state


sealed interface UiState {

    class Input(
        val count: String
    ): UiState
    object Loading : UiState
    companion object {
        val initial = Input("")
    }
}