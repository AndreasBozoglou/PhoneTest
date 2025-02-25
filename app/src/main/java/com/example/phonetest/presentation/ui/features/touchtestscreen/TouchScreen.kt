package com.example.phonetest.presentation.ui.features.touchtestscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun TouchScreen() {
    val pathPoints = remember { mutableStateListOf<Offset>() }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    pathPoints.add(change.position)
                }
            }
    ) {
        val path = Path().apply {
            if (pathPoints.isNotEmpty()) {
                moveTo(pathPoints.first().x, pathPoints.first().y)
                pathPoints.forEach { point ->
                    lineTo(point.x, point.y)
                }
            }
        }
        drawPath(path, Color.Black, style = Stroke(width = 5f, cap = StrokeCap.Round))
    }
}
