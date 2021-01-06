package com.mvestro.blindtify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main_get_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ProcessBuilder.Redirect.from

class MainActivity_getData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_get_data)

        val requestPlaylists = ServiceBuilder.buildService(IPlaylists::class.java)
        val callPlaylists = requestPlaylists.getPlaylists()

        callPlaylists.enqueue(object : Callback<Playlists>{
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
//                Log.d("Playlist", response.body()?.items.toString())
                val allPlaylists = response.body()?.items
                allPlaylists?.let {
                    for (playlist in it){
                        Log.d("Playlist", playlist.getValue())
                    }
                }
            }
            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                Log.e("Playlist", "${t.message}")
            }
        })


        val requestUser = ServiceBuilder.buildService(IUser::class.java)
        val callUser = requestUser.getUser()

        callUser.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                userName.setText("Hello, ${response.body()?.display_name}")
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("User", "${t.message}")
            }
        })

    }





}