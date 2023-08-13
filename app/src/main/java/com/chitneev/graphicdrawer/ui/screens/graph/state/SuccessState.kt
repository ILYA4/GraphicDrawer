package com.chitneev.graphicdrawer.ui.screens.graph.state


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.screens.graph.components.Graph
import com.chitneev.graphicdrawer.ui.screens.graph.components.Table
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun SuccessState(points: List<Point>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Table(
            points = points,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Graph(
            points = points,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
@Preview
private fun SuccessPreview() {
    val points = listOf(
        Point(1.0,2.0),
        Point(2.0,1.0),
        Point(4.0,15.0),
        Point(5.0, 22.04),
        Point(6.0,3.0),
        )
    GraphicDrawerTheme {
        SuccessState(points)
    }
}