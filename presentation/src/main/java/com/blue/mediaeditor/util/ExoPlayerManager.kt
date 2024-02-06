package com.blue.mediaeditor.util

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ExoPlayerManager {

    lateinit var exoPlayer: SimpleExoPlayer

    private val _initState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val initState: StateFlow<Boolean> = _initState
    fun init(context: Context, uri: Uri) {
        exoPlayer = SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
        }
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    _initState.value = true
                }
            }
        })
    }

    fun getMediaLength(): Float {
        return exoPlayer.duration.toFloat()
    }
}