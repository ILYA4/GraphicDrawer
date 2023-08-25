package com.chitneev.graphicdrawer.ui.screens.graph.state

sealed interface Event {
    class ShowErrorSavingGraphToast(val errorMessage: String): Event
    class ShowSuccessSavingGraphToast(val fileName: String): Event
}