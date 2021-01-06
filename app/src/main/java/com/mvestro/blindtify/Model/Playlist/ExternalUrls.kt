package com.mvestro.blindtify.Model.Playlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExternalUrls {

    @SerializedName("spotify")
    @Expose
    private var spotify: String? = null

    fun getSpotify(): String? {
        return spotify
    }

    fun setSpotify(spotify: String?) {
        this.spotify = spotify
    }

    fun withSpotify(spotify: String?): ExternalUrls? {
        this.spotify = spotify
        return this
    }
}