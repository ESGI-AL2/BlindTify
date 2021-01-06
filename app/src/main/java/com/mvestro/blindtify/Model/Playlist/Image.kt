package com.mvestro.blindtify.Model.Playlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image {
    @SerializedName("height")
    @Expose
    private var height: Int? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null

    @SerializedName("width")
    @Expose
    private var width: Int? = null

    fun getHeight(): Int? {
        return height
    }

    fun setHeight(height: Int?) {
        this.height = height
    }

    fun withHeight(height: Int?): Image? {
        this.height = height
        return this
    }

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

    fun getWidth(): Int? {
        return width
    }

    fun setWidth(width: Int?) {
        this.width = width
    }

    fun withWidth(width: Int?): Image? {
        this.width = width
        return this
    }

}