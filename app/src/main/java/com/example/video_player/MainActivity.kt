package com.example.video_player

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {
    private lateinit var exoPlayer: SimpleExoPlayer

    private val ipAddress = "192.168.1.141"
    private val port = 12345
    private val value = "stream"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // val rtspUri = "rtsp://192.168.1.141:12345/stream"
        val rtspUri = "rtsp://$ipAddress:$port/$value"

        val mediaSource: MediaSource =
            RtspMediaSource.Factory().createMediaSource(MediaItem.fromUri(rtspUri))

        exoPlayer = SimpleExoPlayer.Builder(this).build()

        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        val playerView = findViewById<PlayerView>(R.id.exoPlayerView)
        playerView.player = exoPlayer
        Log.i("Main Activity","Message")

    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the player when the activity is destroyed.
        exoPlayer.release()
    }
}




