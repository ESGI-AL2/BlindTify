package com.mvestro.blindtify

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvestro.blindtify.Model.User
import com.mvestro.blindtify.Model.Item
import com.mvestro.blindtify.Model.Playlist
import com.mvestro.blindtify.Service.BuilderService
import com.mvestro.blindtify.Service.PlaylistService
import com.mvestro.blindtify.Service.SpotifyService
import com.mvestro.blindtify.Service.UserService
import kotlinx.android.synthetic.main.activity_in_game.*
import kotlinx.android.synthetic.main.activity_playlists_list.*
import kotlinx.android.synthetic.main.activity_playlists_list.CoErr
import kotlinx.android.synthetic.main.activity_playlists_list.btnOkQuit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsList : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists_list)

        linearLayoutManager = LinearLayoutManager(this)
        PlaylistsView.layoutManager = linearLayoutManager

        val PlaylistService = BuilderService.buildService(PlaylistService::class.java)
        val playlistRequest = PlaylistService.getPlaylist("Bearer ${SpotifyService.getToken()}")

        var playlists: ArrayList<Item?>

        if (isNetworkConnected()) {


            playlistRequest.enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    playlists = response.body()?.items as ArrayList<Item?>

                    adapter = RecyclerAdapter(playlists)
                    PlaylistsView.adapter = adapter
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    Log.d("Retrofit", "$t")
                }
            })


            val username = findViewById<TextView>(R.id.username)

            val UserService = BuilderService.buildService(UserService::class.java)
            val UserRequest = UserService.getUser("Bearer ${SpotifyService.getToken()}")

            UserRequest.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user: User = response.body()!!
                    username.text = "${username.text} ${user.display_name}"
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("Retrofit", "$t")
                }
            })
        } else {
            CoErr.isGone = false
            btnOkQuit.setOnClickListener {
                finishAffinity()
            }
            Toast.makeText(this, R.string.Redemarrer, Toast.LENGTH_LONG)
                .show()
        }


    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            false
        }
    }
}