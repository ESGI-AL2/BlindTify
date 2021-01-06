package com.mvestro.blindtify.Model.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image {
    @SerializedName("url")
    @Expose
    private var url: String? = null

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun withUrl(url: String?): Image? {
        this.url = url
        return this
    }

}