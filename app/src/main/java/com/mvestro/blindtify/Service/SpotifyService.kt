package com.mvestro.blindtify.Service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.mvestro.blindtify.MainActivity
import com.mvestro.blindtify.R
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.*
import com.spotify.protocol.client.Subscription
import com.spotify.protocol.types.PlayerContext
import com.spotify.protocol.types.Track
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import java.util.*

object SpotifyService {

    private const val REDIRECT_URI = "http://10.0.2.2:8888/callback"
    private const val CLIENT_ID = "9f138a63545645868a5d512bc3f29396"
    private var spotifyAppRemote: SpotifyAppRemote? = null
    const val AUTH_TOKEN_REQUEST_CODE = 0x10
    private var mAccessToken: String? = null

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

    fun connectSDK(activity: Activity) {
        SpotifyAppRemote.connect(activity, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
            }

            override fun onFailure(throwable: Throwable) {
                if (throwable is NotLoggedInException || throwable is UserNotAuthorizedException) {
                    Toast.makeText(activity, R.string.NotLoggedInException, Toast.LENGTH_LONG)
                        .show()
                    // Show login button and trigger the login flow from auth library when clicked
                } else if (throwable is CouldNotFindSpotifyApp) {
                    Toast.makeText(activity, R.string.CouldNotFindSpotifyApp, Toast.LENGTH_LONG)
                        .show()
                } else if (throwable is AuthenticationFailedException) {
                    Toast.makeText(
                        activity,
                        R.string.AuthenticationFailedException,
                        Toast.LENGTH_LONG
                    ).show()
                }
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

    fun assertAppRemoteConnected(): SpotifyAppRemote {
        spotifyAppRemote?.let {
            if (it.isConnected) {
                return it
            }
        }
        throw SpotifyDisconnectedException()
    }

    fun playUri(uri: String) {
        assertAppRemoteConnected()
            .playerApi
            .play(uri)
    }

    fun shuffle() {
        assertAppRemoteConnected()
            .playerApi
            .setShuffle(true)
    }

    fun nextTrack() {
        assertAppRemoteConnected()
            .playerApi
            .skipNext()
    }

    fun pause() {
        assertAppRemoteConnected()
            .playerApi
            .pause()
    }

    fun resume() {
        assertAppRemoteConnected()
            .playerApi
            .resume()
    }

}