package com.mvestro.blindtify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvestro.blindtify.Model.User.User
import com.mvestro.blindtify.MainActivity
import com.mvestro.blindtify.Model.Playlist.Item
import com.mvestro.blindtify.Model.Playlist.Playlist
import com.mvestro.blindtify.Service.BuilderService
import com.mvestro.blindtify.Service.PlaylistService
import com.mvestro.blindtify.Service.SpotifyService
import com.mvestro.blindtify.Service.UserService
import kotlinx.android.synthetic.main.activity_playlists_list.*
import kotlinx.android.synthetic.main.playlist_name_uri.*
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

        playlistRequest.enqueue(object : Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                playlists = response.body()?.getItems() as ArrayList<Item?>

                adapter = RecyclerAdapter(playlists)
                PlaylistsView.adapter = adapter


                /*var playlistsName = mutableListOf<String>()
                var playlistsUri = mutableListOf<String>()

                for (c in playlists?.getItems()!!){
                    if (c != null) {
                        playlistsName.add(c.getName().toString())
                        playlistsUri.add(c.getUri().toString())
                    }
                }*/
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
                username.text = "Hello, ${user.getDisplayName()}"
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Retrofit", "$t")
            }
        })


    }
}