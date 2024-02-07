package com.blue.mediaeditor.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.blue.mediaeditor.ui.component.TimeLineComponent
import com.blue.mediaeditor.util.ExoPlayerManager
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProjectScreen(
    navController: NavController,
){
    val exoPlayer = remember{ ExoPlayerManager.exoPlayer }
    val lazyRowState = rememberLazyListState()
    val firstItem = remember { mutableIntStateOf(0) }
    val secondItem = remember { mutableIntStateOf(0) }
    var isChecking = false


    fun checkPosition() {
        CoroutineScope(Dispatchers.Main).launch {
            while (exoPlayer.isPlaying) {
                val lazyRowPosition = secondItem.intValue * (exoPlayer.currentPosition.toFloat() / exoPlayer.duration)
                lazyRowState.scrollToItem(0, lazyRowPosition.toInt())
                delay(100L)
            }
            lazyRowState.scrollToItem(0, secondItem.intValue)
            exoPlayer.pause()
        }
    }

    exoPlayer.addAnalyticsListener(
        object : AnalyticsListener {
            override fun onPlaybackStateChanged(
                eventTime: AnalyticsListener.EventTime, @Player.State state: Int
            ) {}

            override fun onIsPlayingChanged(
                eventTime: AnalyticsListener.EventTime,
                isPlaying: Boolean
            ) {
                if(!isChecking && isPlaying) {
                    isChecking = true
                    checkPosition()
                    isChecking = false
                }
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.DarkGray
    ) { padding ->
        Column(
            modifier= Modifier.padding(padding)
        ){
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        this.useController = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(onClick = { ExoPlayerManager.play() }) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { ExoPlayerManager.pause() }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }
            TimeLineComponent(lazyRowState, firstItem, secondItem){
                exoPlayer.seekTo((ExoPlayerManager.getMediaLength() * (it/100)).toLong())
            }
        }
    }
}