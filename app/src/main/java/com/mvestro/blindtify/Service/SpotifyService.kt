package com.mvestro.blindtify.Service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.ListView
import com.mvestro.blindtify.MainActivity
import com.mvestro.blindtify.R
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.SpotifyDisconnectedException
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

object SpotifyService {

    private const val REDIRECT_URI = "http://10.0.2.2:8888/callback"
    private const val CLIENT_ID = "9f138a63545645868a5d512bc3f29396"
    private const val SCOPE = "playlist-modify-public playlist-modify-private playlist-read-private user-modify-playback-state"
    private var spotifyAppRemote: SpotifyAppRemote? = null
    const val AUTH_TOKEN_REQUEST_CODE = 0x10
    private var mAccessToken: String? = null
    private const val url = "https://api.spotify.com/"
    private lateinit var listView: ListView

    fun getToken(): String? {
        return mAccessToken
    }

    val connectionParams = ConnectionParams
        .Builder(CLIENT_ID)
        .setRedirectUri(REDIRECT_URI)
        .showAuthView(true)
        .build()

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
        return AuthorizationRequest.Builder(CLIENT_ID, type, REDIRECT_URI).build()
    }

    fun connectSDK(activity: Activity){
        SpotifyAppRemote.connect(activity, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.i("TOKEN", "Connected! Yay!")
            }

            override fun onFailure(throwable: Throwable) {
                Log.i("TOKEN", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })

        val request: AuthorizationRequest =
            getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(activity, AUTH_TOKEN_REQUEST_CODE, request)
    }

    fun connectAPI(requestCode: Int, resultCode: Int, data: Intent?): String? {
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            mAccessToken = response.accessToken
        }
        return mAccessToken
    }

    fun playUri(uri: String) {
        assertAppRemoteConnected()
            .playerApi
            .play(uri)
            .setResultCallback { Log.i("PLAYER", "play") }
    }

    private fun assertAppRemoteConnected(): SpotifyAppRemote {
        spotifyAppRemote?.let {
            if (it.isConnected) {
                return it
            }
        }
        Log.e("Connect", R.string.err_spotify_disconnected.toString())
        throw SpotifyDisconnectedException()
        throw SpotifyDisconnectedException()
    }


}