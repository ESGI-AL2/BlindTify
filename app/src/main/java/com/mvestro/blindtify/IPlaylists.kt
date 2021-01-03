package com.mvestro.blindtify

import com.mvestro.blindtify.Playlists
import retrofit2.Call
import retrofit2.http.GET

interface IPlaylists {

    @GET("/Playlist.json")
    fun getPlaylists(): Call<Playlists>
}