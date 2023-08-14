package com.chitneev.graphicdrawer.ui.screens.graph.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun Graph(
    points: List<Point>,
    modifier: Modifier = Modifier,
) {

    val chartEntryModel = entryModelOf(
        points.map { FloatEntry(it.x.toFloat(), it.y.toFloat()) }
    )

    Chart(
        chart = lineChart(),
        model = chartEntryModel,
        startAxis = rememberStartAxis(),
        bottomAxis = rememberBottomAxis(),
        getXStep = { _ -> 10f },
        modifier = modifier
    )
}

@Preview
@Composable
private fun GraphPreview() {
    val points = listOf(
        Point(1.0,2.0),
        Point(3.0,1.0),
        Point(4.0,3.0),
        Point(5.0,15.0),
        Point(6.0, 22.04),
        )

    GraphicDrawerTheme {
        Graph(points = points)
    }
}

