package com.mvestro.blindtify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main_get_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity_getData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_get_data)

        getPlaylists()
    }

    internal fun getPlaylists(){
        val playlistsURL = "https://spotify-c7716.firebaseio.com"

        val retrofit = Retrofit.Builder()
                .baseUrl(playlistsURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(IPlaylists::class.java)

        val playlists: Call<Playlists> = service.getPlaylists()

        playlists.enqueue(object: Callback<Playlists>{

            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>){
                response.body()?.let { Log.d("Playlist", "nom : ${it.name}") }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable){
                Log.e("Playlist", "Error : $t")
            }

        })
    }



}