package com.mvestro.blindtify

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mvestro.blindtify.PartyActivity.SpotifySampleContexts.PLAYLIST_URI
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.SpotifyDisconnectedException
import com.spotify.protocol.types.Track
import kotlinx.android.synthetic.main.activity_party.*
import java.util.*

class PartyActivity : AppCompatActivity() {

    private var spotifyAppRemote: SpotifyAppRemote? = null
    private val errorCallback = { throwable: Throwable -> logError(throwable) }

    private val clientId = "9f138a63545645868a5d512bc3f29396"
    private val redirectUri = "http://10.0.2.2:8888/callback"

    object SpotifySampleContexts {
        const val TRACK_URI = "spotify:track:4IWZsfEkaK49itBwCTFDXQ"
        const val ALBUM_URI = "spotify:album:4m2880jivSbbyEGAKfITCa"
        const val ARTIST_URI = "spotify:artist:3WrFJ7ztbogyGnTHbHJFl2"
        const val PLAYLIST_URI = "spotify:playlist:37i9dQZEVXbMDoHDwVN2tF"
        const val PODCAST_URI = "spotify:show:2tgPYIeGErjk6irHRhk9kj"
    }

    companion object {
        const val TAG = "App-Remote Sample"
        const val STEP_MS = 15000L;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)

        progressBar.max = 10

        val currentProgress = 10

        ObjectAnimator.ofInt(progressBar,"progress",currentProgress)
            .setDuration(5000)
            .start()

        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()
    }

    override fun onStart() {
        super.onStart()
        connected()
    }

    private fun assertAppRemoteConnected(): SpotifyAppRemote {
        spotifyAppRemote?.let {
            if (it.isConnected) {
                return it
            }
        }
        Log.e(TAG, "Error")
        throw SpotifyDisconnectedException()
    }

    private fun connected() {
        spotifyAppRemote?.let {
            // Play a playlist
            val playlistURI = "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"
            it.playerApi.play(playlistURI)
            // Subscribe to PlayerState
            it.playerApi.subscribeToPlayerState().setEventCallback {
                val track: Track = it.track
                Log.d("MainActivity", track.name + " by " + track.artist.name)
            }
        }

    }

    private fun logError(throwable: Throwable) {
        Toast.makeText(this, "$throwable", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "", throwable)
    }

    private fun logMessage(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
        Log.d(TAG, msg)
    }
}