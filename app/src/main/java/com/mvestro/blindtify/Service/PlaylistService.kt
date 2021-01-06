package com.mvestro.blindtify.Service

import com.mvestro.blindtify.Model.Playlist.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface PlaylistService {

    @GET("/v1/me/playlists")
    fun getPlaylist(@Header("Authorization") TOKEN: String): Call<Playlist>

}