package com.chitneev.graphicdrawer.ui.screens.graph.state


import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.domain.models.Point
import com.chitneev.graphicdrawer.ui.screens.graph.components.Graph
import com.chitneev.graphicdrawer.ui.screens.graph.components.Table
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun SuccessState(
    points: List<Point>,
    onBitmapCreated: (Bitmap) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val view = LocalView.current
        var composableBounds by remember {
            mutableStateOf<Rect?>(null)
        }

        Table(
            points = points,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Graph(
            points = points,
            modifier = Modifier
                .padding(12.dp)
                .onGloballyPositioned {
                    composableBounds = it.boundsInWindow()
                }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            val bitmap = Bitmap.createBitmap(
                composableBounds!!.width.toInt(),
                composableBounds!!.height.toInt(),
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
                .apply {
                    translate(-composableBounds!!.left, -composableBounds!!.top)
                }
            view.draw(canvas)
            canvas.setBitmap(null)
            onBitmapCreated.invoke(bitmap)
        }) {
            Text(text = stringResource(id = R.string.save_graph_button_text))
        }
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
       SuccessState(points) {}
    }
}