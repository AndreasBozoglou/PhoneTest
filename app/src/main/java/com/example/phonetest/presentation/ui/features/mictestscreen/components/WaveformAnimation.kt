package com.example.phonetest.presentation.ui.features.mictestscreen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WaveformAnimation(
    modifier: Modifier = Modifier,
    barColor: Color = Color.Red,
    barCount: Int = 20
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedValues = List(barCount) {
        infiniteTransition.animateFloat(
            initialValue = 10f,
            targetValue = 50f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = (300..700).random(),
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val barWidth = size.width / (barCount * 2f)
        val spacing = barWidth

        animatedValues.forEachIndexed { index, anim ->
            val x = index * (barWidth + spacing)
            drawRoundRect(
                color = barColor,
                topLeft = Offset(x, size.height / 2 - anim.value / 2),
                size = Size(barWidth, anim.value),
                cornerRadius = CornerRadius(8f, 8f)
            )
        }
    }
}