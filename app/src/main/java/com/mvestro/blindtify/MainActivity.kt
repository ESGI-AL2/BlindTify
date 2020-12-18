package com.mvestro.blindtify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.AuthenticationFailedException
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp
import com.spotify.android.appremote.api.error.NotLoggedInException
import com.spotify.android.appremote.api.error.UserNotAuthorizedException
import com.spotify.protocol.types.Track

class MainActivity : AppCompatActivity() {

    private val clientId = "9f138a63545645868a5d512bc3f29396"
    private val redirectUri = "http://10.0.2.2:8888/callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById(R.id.BtnLogin) as Button
        btnLogin.setOnClickListener {
            onStart()
        }

        val btnLogoff = findViewById(R.id.BtnLogOff) as Button
        btnLogoff.setOnClickListener {
            onStop()
        }

        val btnStop = findViewById(R.id.BtnPause) as Button
        btnStop.setOnClickListener {
            Stop()
        }

        val btnPlay = findViewById(R.id.BtnPlay) as Button
        btnPlay.setOnClickListener {
            connected()
        }
    }


    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams.Builder(clientId)
                .setRedirectUri(redirectUri)
                .showAuthView(true)
                .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.i("AppRemote","Connecté !")
            }

            override fun onFailure(throwable: Throwable) {
                Log.i("MainActivity", throwable.message, throwable)

                if (throwable is NotLoggedInException || throwable is UserNotAuthorizedException) {
                    val toast = Toast.makeText(applicationContext, "NotLoggedInException ou UserNotAuthorizedException", Toast.LENGTH_LONG)
                    toast.show()
                    // Show login button and trigger the login flow from auth library when clicked
                } else if (throwable is CouldNotFindSpotifyApp) {
                    val toast = Toast.makeText(applicationContext, "Spotify n'est pas installé sur votre téléphone", Toast.LENGTH_LONG)
                    toast.show()
                } else if (throwable is AuthenticationFailedException || throwable is AuthenticationFailedException) {
                    val toast = Toast.makeText(applicationContext, "Erreur ID client ou Invalid app identifier (iOS Bundle ID, Android Key Hash)", Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        })
    }

    private fun connected() {
        spotifyAppRemote?.let {
            // Play a playlist
            val playlistURI = "spotify:playlist:37i9dQZF1DX1X23oiQRTB5"
            it.playerApi.play(playlistURI)
            // Subscribe to PlayerState
            it.playerApi.subscribeToPlayerState().setEventCallback {
                val track: Track = it.track
                Log.i("AppRemote", "Play " + track.name + " by " + track.artist.name)
            }
        }
    }

    private fun Stop() {
        spotifyAppRemote?.let {
            it.playerApi.pause()
            // Subscribe to PlayerState
            it.playerApi.subscribeToPlayerState().setEventCallback {
                val track: Track = it.track
                Log.i("AppRemote", "Pause " + track.name + " by " + track.artist.name)
            }
        }

    }

    override fun onStop() {
        super.onStop()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
        }

    }
}