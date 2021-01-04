package com.mvestro.blindtify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.gson.internal.`$Gson$Types`
import com.mvestro.blindtify.Model.Course
import com.mvestro.blindtify.Model.Playlist
import com.mvestro.blindtify.Model.Repo
import com.mvestro.blindtify.Service.PlayService
import com.mvestro.blindtify.Service.UsersService
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        private const val REDIRECT_URI = "http://10.0.2.2:8888/callback"
        private const val CLIENT_ID = "9f138a63545645868a5d512bc3f29396"
        private var spotifyAppRemote: SpotifyAppRemote? = null
        const val AUTH_TOKEN_REQUEST_CODE = 0x10
        private var mAccessToken: String? = null
        private const val url = "https://api.spotify.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("MainActivity", "Connected! Yay!")
                // Now you can start interacting with App Remote
                connected()
            }

            override fun onFailure(throwable: Throwable) {
                Log.e("MainActivity", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })
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

    fun onGetUserProfilClicked(view: View?) {
        if (mAccessToken == null) {
            val snackbar = Snackbar.make(
                findViewById(R.id.activity_main),
                R.string.warning_need_token,
                Snackbar.LENGTH_SHORT
            )
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            snackbar.show()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(UsersService::class.java)
        val courseRequest = service.listRepos()

        courseRequest.enqueue(object : Callback<List<Playlist>> {
            override fun onResponse(
                call: Call<List<Playlist>>,
                response: Response<List<Playlist>>
            ) {
                val allCourse = response.body()
                if (allCourse != null) {
                    Log.i("Retrofit", "HERE is ALL COURSES FROM HEROKU SERVER:")
                    for (c in allCourse)
                        Log.i("Retrofit", " one course : ${c.display_name}")
                }
            }

            override fun onFailure(call: Call<List<Playlist>>, t: Throwable) {
                Log.i("Retrofit", "$t")
            }
        })

    }

    fun playToMusic(view: View?) {
        if (mAccessToken == null) {
            val snackbar = Snackbar.make(
                findViewById(R.id.activity_main),
                R.string.warning_need_token,
                Snackbar.LENGTH_SHORT
            )
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            snackbar.show()
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(PlayService::class.java)
        val courseRequest = service.play()
        courseRequest.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                Log.i("Retrofit",response.body().toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("Retrofit", "$t")
            }
        })
    }

    fun onRequestTokenClicked(view: View?) {
        val request: AuthorizationRequest =
            getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(this, MainActivity.AUTH_TOKEN_REQUEST_CODE, request)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            mAccessToken = response.accessToken
            updateTokenView()
        }
    }

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
        return AuthorizationRequest.Builder(CLIENT_ID, type, REDIRECT_URI).build()
    }

    private fun updateTokenView() {
        val tokenView = findViewById<TextView>(R.id.token_text_view)
        tokenView.text = getString(R.string.token, mAccessToken)
    }


}