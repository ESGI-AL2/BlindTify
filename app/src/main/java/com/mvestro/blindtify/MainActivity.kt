package com.mvestro.blindtify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mvestro.blindtify.Model.Playlist.Playlist
import com.mvestro.blindtify.Model.User.User
import com.mvestro.blindtify.Service.BuilderService
import com.mvestro.blindtify.Service.PlaylistService
import com.mvestro.blindtify.Service.UserService
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        private const val REDIRECT_URI = "http://10.0.2.2:8888/callback"
        private const val CLIENT_ID = "9f138a63545645868a5d512bc3f29396"
        private const val SCOPE = "playlist-modify-public playlist-modify-private playlist-read-private user-modify-playback-state"
        private var spotifyAppRemote: SpotifyAppRemote? = null
        const val AUTH_TOKEN_REQUEST_CODE = 0x10
        private var mAccessToken: String? = null
        private const val url = "https://api.spotify.com/"
        private lateinit var listView: ListView

        fun getmAccessToken(): String? {
            return mAccessToken
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSeePlaylists = findViewById<Button>(R.id.btnSeePlaylists)

        btnSeePlaylists.setOnClickListener{
            val intent = Intent(this, PlaylistsList::class.java)
            startActivity(intent)
        }

    }


    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams
            .Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("MainActivity", "Connected! Yay!")
            }

            override fun onFailure(throwable: Throwable) {
                Log.e("MainActivity", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })

        val request: AuthorizationRequest =
            getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(this, MainActivity.AUTH_TOKEN_REQUEST_CODE, request)
    }

    fun authGuard(){
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
    }

    fun onGetUserProfilClicked(view: View?) {
        authGuard()

        val service = BuilderService.buildService(UserService::class.java)
        val userRequest = service.getUser("Bearer $mAccessToken")

        userRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user: User = response.body()!!
                Log.d("Response", user.getFollowers()?.getTotal().toString())
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("Retrofit", "$t")
            }
        })

    }

    fun onGetUserPlaylistClicked(view: View?){
        authGuard()

        val service = BuilderService.buildService(PlaylistService::class.java)
        val playlistRequest = service.getPlaylist("Bearer $mAccessToken")

        playlistRequest.enqueue(object : Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                afficher(response.body()!!)
            }
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                Log.d("Retrofit", "$t")
            }
        })

    }

    fun afficher(playlist: Playlist){
        for (c in playlist.getItems()!!){
            if (c != null) {
                Log.d("Response", c.getName().toString())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
        Log.i("TOKEN", "$mAccessToken")
    }


}