package com.blue.mediaeditor.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.blue.mediaeditor.util.ExoPlayerManager
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun ProjectScreen(
    navController: NavController,
){
    val exoPlayer = remember{ ExoPlayerManager.exoPlayer }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    exoPlayer.addAnalyticsListener(
        object : AnalyticsListener {
            override fun onPlaybackStateChanged(
                eventTime: AnalyticsListener.EventTime, @Player.State state: Int
            ) {
                sliderPosition = eventTime.currentPlaybackPositionMs.toFloat()
                Log.e("TAG", "onPlaybackStateChanged: ${eventTime.currentPlaybackPositionMs}")
                Log.e("TAG", "onPlaybackStateChanged: ${eventTime.eventPlaybackPositionMs}")
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
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomSlider(sliderPosition){
                sliderPosition = it
                exoPlayer.seekTo(it.toLong())
            }
        }

    }
}

@Composable
fun CustomSlider(
    position: Float,
    onClick:(Float)->Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Slider(
            value = position,
            onValueChange = { onClick(it) },
            valueRange = 0f..ExoPlayerManager.getMediaLength()
        )
        Text(text = "현 위치 : ${position}")
    }
}