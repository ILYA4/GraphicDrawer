package com.chitneev.graphicdrawer.ui.screens.graph.state

import com.chitneev.graphicdrawer.domain.models.Point

data class UiState(
    val points: List<Point>,
) {
    companion object {
        val initial = UiState(emptyList())
    }
}