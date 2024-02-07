package com.blue.mediaeditor.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun TimeLineComponent(
    lazyRowState: LazyListState,
    firstItem: MutableIntState,
    secondItem: MutableIntState,
    onClick: (Double) -> Unit
) {
    LaunchedEffect(lazyRowState) {
        snapshotFlow { lazyRowState.firstVisibleItemScrollOffset }
            .collect { offset ->
                if(lazyRowState.isScrollInProgress)
                    onClick( offset.toDouble() / secondItem.intValue * 100 )
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            state = lazyRowState,
            modifier = Modifier.fillMaxWidth(),
        ) {
            item{
                Box(
                    modifier = Modifier
                        .size(width = (LocalConfiguration.current.screenWidthDp / 2 + 1).dp, height = 0.dp)
                        .background(Color(0x00FF0000))
                        .onGloballyPositioned { layoutCoordinates ->
                            val width = layoutCoordinates.size.width
                            firstItem.intValue = width
                        }

                )
            }
            item {
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .background(Color(0xFFFFFFFF))
                        .onGloballyPositioned { layoutCoordinates ->
                            val width = layoutCoordinates.size.width
                            secondItem.intValue = width
                        }
                )
            }
            item{
                Box(
                    modifier = Modifier
                        .size(width = (LocalConfiguration.current.screenWidthDp / 2).dp, height = 0.dp)
                        .background(Color(0x00FF0000))
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color(0xFFFF0000))
        )
    }
}