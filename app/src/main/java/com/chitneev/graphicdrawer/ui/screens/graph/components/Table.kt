package com.chitneev.graphicdrawer.ui.screens.graph.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun Table(
    points: List<Point>,
    modifier: Modifier = Modifier,
    ) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
        ) {
            TableBox(text = stringResource(id = R.string.table_empty))
            TableBox(text = stringResource(id = R.string.table_x))
            TableBox(text = stringResource(id = R.string.table_y))
        }

        points.forEach { point ->
            Row {
                TableBox(text = "")
                TableBox(text = point.x.toString())
                TableBox(text = point.y.toString())
            }
        }
    }
}

@Composable
private fun RowScope.TableBox(
    text: String,
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(1f)
            .padding(8.dp)
    )
}

@Preview
@Composable
private fun TablePreview() {
    GraphicDrawerTheme {
        val points = mutableListOf(
            Point(1.3, 1.4),
            Point(5.4, 5.4),
            Point(3.4, 5.6)
        )
        Table(points = points)
    }
}