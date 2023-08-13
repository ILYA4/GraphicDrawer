package com.chitneev.graphicdrawer.ui.screens.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chitneev.graphicdrawer.R
import com.chitneev.graphicdrawer.ui.theme.GraphicDrawerTheme

@Composable
fun MainScreen(
    onNavigateToGraphScreen: (Int) -> Unit,
) {
    Content(onRequestClick = onNavigateToGraphScreen)
}

@Composable
private fun Content(
    onRequestClick: (Int) -> Unit
) {
    val context = LocalContext.current
    var countPoint by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.points_count_text))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = countPoint,
            onValueChange = { value -> countPoint = value },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.None
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val countPointInt = countPoint.toIntOrNull()
            if (countPointInt != null && countPointInt > 0) {
                onRequestClick(countPointInt)
            } else {
                Toast.makeText(context, R.string.count_point_more_than_zero, Toast.LENGTH_SHORT).show()
            }
        })
        {
            Text(text = stringResource(id = R.string.request_points_button_text))
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    GraphicDrawerTheme {
        Content {}
    }
}