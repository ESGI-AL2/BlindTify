package com.mvestro.blindtify.Model.Playlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner {
    @SerializedName("display_name")
    @Expose
    private var displayName: String? = null

    @SerializedName("external_urls")
    @Expose
    private var externalUrls: ExternalUrls? = null

    @SerializedName("href")
    @Expose
    private var href: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("uri")
    @Expose
    private var uri: String? = null

    fun getDisplayName(): String? {
        return displayName
    }

    fun setDisplayName(displayName: String?) {
        this.displayName = displayName
    }

    fun withDisplayName(displayName: String?): Owner? {
        this.displayName = displayName
        return this
    }

    fun getExternalUrls(): ExternalUrls? {
        return externalUrls
    }

    fun setExternalUrls(externalUrls: ExternalUrls?) {
        this.externalUrls = externalUrls
    }

    fun withExternalUrls(externalUrls: ExternalUrls?): Owner? {
        this.externalUrls = externalUrls
        return this
    }

    fun getHref(): String? {
        return href
    }

    fun setHref(href: String?) {
        this.href = href
    }

    fun withHref(href: String?): Owner? {
        this.href = href
        return this
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun withId(id: String?): Owner? {
        this.id = id
        return this
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun withType(type: String?): Owner? {
        this.type = type
        return this
    }

    fun getUri(): String? {
        return uri
    }

    fun setUri(uri: String?) {
        this.uri = uri
    }

    fun withUri(uri: String?): Owner? {
        this.uri = uri
        return this
    }
}