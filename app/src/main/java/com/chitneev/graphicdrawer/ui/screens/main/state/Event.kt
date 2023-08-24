package com.chitneev.graphicdrawer.ui.screens.main.state

sealed interface Event {
    object NavigateToGraph: Event
    object ShowErrorLoadingToast: Event
}