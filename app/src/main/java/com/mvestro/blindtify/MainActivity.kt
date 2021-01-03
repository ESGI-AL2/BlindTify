package com.mvestro.blindtify

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.activity_party.*

class MainActivity : AppCompatActivity() {

    private val clientId = "9f138a63545645868a5d512bc3f29396"
    private val redirectUri = "http://10.0.2.2:8888/callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.max = 10

        val currentProgress = 10

        ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
            .setDuration(5000)
            .start()

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
                authGuard()
            }

            override fun onFailure(throwable: Throwable) {
                Log.i("MainActivity", throwable.message, throwable)

                if (throwable is NotLoggedInException || throwable is UserNotAuthorizedException) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "NotLoggedInException ou UserNotAuthorizedException",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    // Show login button and trigger the login flow from auth library when clicked
                } else if (throwable is CouldNotFindSpotifyApp) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Spotify n'est pas installé sur votre téléphone $throwable",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                } else if (throwable is AuthenticationFailedException || throwable is AuthenticationFailedException) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Erreur ID client ou Invalid app identifier (iOS Bundle ID, Android Key Hash)",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                }
            }
        })
    }

    fun authGuard() {
        val intent = Intent(this, MainLoginActivity::class.java)
        startActivity(intent)
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

    fun tacliquer(view: View?) {
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}