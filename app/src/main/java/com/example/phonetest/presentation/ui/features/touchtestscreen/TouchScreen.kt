package com.example.phonetest.presentation.ui.features.touchtestscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.phonetest.utils.phoneTestNavigatePopUp

data class GridItem(val id: Int, var isActive: Boolean = false)
@Composable
fun TouchScreen(navController: NavController) {
    /*val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    val cellSize = 40.dp
    val gridSize = screenWidthDp / cellSize.value.toInt()
    val totalRows = screenHeightDp / cellSize.value.toInt()
    val totalCells = gridSize * totalRows*/
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val cellSize = 40.dp

    val displayMetrics = context.resources.displayMetrics
    val screenWidthPx = displayMetrics.widthPixels.toFloat()
    val screenHeightPx = displayMetrics.heightPixels.toFloat()

    val cellSizePx = with(density) { cellSize.toPx() }

    val insets = WindowInsets.systemBars.asPaddingValues()
    val navBarHeightPx = with(density) { insets.calculateBottomPadding().toPx() }
    val systemBarHeightPx = with(density) { insets.calculateTopPadding().toPx() }

    val adjustedScreenHeightPx = if (navBarHeightPx > 0 && systemBarHeightPx > 0) {
        screenHeightPx + navBarHeightPx + systemBarHeightPx
    } else screenHeightPx

    val gridSize = (screenWidthPx / cellSizePx).toInt()
    val totalRows = (adjustedScreenHeightPx / cellSizePx).toInt()
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
                            navController.phoneTestNavigatePopUp()
                        }
                    }
                }
            }
    ) {

        itemsIndexed(items) { index, item ->
            Log.i("items indexed", "${items.size}")
            Box(
                modifier = Modifier
                    .size(cellSize)
                    .padding(2.dp)
                    .aspectRatio(1f)
                    .background(if (item.isActive) Color.Blue else Color.LightGray)
                    .clickable {
                        items[index] = items[index].copy(isActive = true)
                        if (items.all { it.isActive }) {
                            navController.phoneTestNavigatePopUp()
                        }
                    }
            )
        }
    }
}
