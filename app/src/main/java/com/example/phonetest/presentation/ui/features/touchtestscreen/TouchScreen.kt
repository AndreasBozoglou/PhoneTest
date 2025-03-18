package com.example.phonetest.presentation.ui.features.touchtestscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.phonetest.utils.phoneTestNavigatePopUp
import kotlin.math.ceil

data class GridItem(val id: Int, var isActive: Boolean = false)
@Composable
fun TouchScreen(navController: NavController) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val cellSize = 40.dp
    val cellSizePx = with(density) { cellSize.toPx() }

    var usableHeightPx by remember { mutableFloatStateOf(0f) }

    val rootView = LocalView.current
    LaunchedEffect(rootView) {
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            usableHeightPx = rootView.height.toFloat()
        }
    }

    val displayMetrics = context.resources.displayMetrics
    val screenWidthPx = displayMetrics.widthPixels.toFloat()

    if (usableHeightPx > 0f) {
        val gridSize = (screenWidthPx / cellSizePx).toInt()
        val totalRowsFloat = usableHeightPx / cellSizePx

        val totalRows =
            if (totalRowsFloat - totalRowsFloat.toInt() > 0.1) ceil(totalRowsFloat).toInt() else totalRowsFloat.toInt()
        val totalCells = gridSize * totalRows
        val items = remember {
            mutableStateListOf<GridItem>().apply {
                repeat(totalCells) { add(GridItem(it)) }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val position = event.changes.firstOrNull()?.position ?: continue
                            val column = (position.x / cellSizePx).toInt()
                            val row = (position.y / cellSizePx).toInt()
                            val index = row * gridSize + column

                            if (index in items.indices) {
                                items[index] = items[index].copy(isActive = true)
                            }
                            if (items.all { it.isActive }) {
                                Log.d("items", "all are active")
                                navController.phoneTestNavigatePopUp()
                            }
                        }
                    }
                }
        ) {
            Log.d("items", "${items.size}")
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .size(cellSize)
                        .padding(2.dp)
                        .aspectRatio(1f)
                        .background(if (item.isActive) Color.Blue else Color.LightGray)
                )
            }
        }
    }
}
