package com.mvestro.blindtify.Model.User

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class User{
    @SerializedName("display_name")
    @Expose
    private var displayName: String? = null

    @SerializedName("external_urls")
    @Expose
    private var externalUrls: ExternalUrls? = null

    @SerializedName("followers")
    @Expose
    private var followers: Followers? = null

    @SerializedName("href")
    @Expose
    private var href: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("images")
    @Expose
    private var images: List<Image?>? = null

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

    fun withDisplayName(displayName: String?): User? {
        this.displayName = displayName
        return this
    }

    fun getExternalUrls(): ExternalUrls? {
        return externalUrls
    }

    fun setExternalUrls(externalUrls: ExternalUrls?) {
        this.externalUrls = externalUrls
    }

    fun withExternalUrls(externalUrls: ExternalUrls?): User? {
        this.externalUrls = externalUrls
        return this
    }

    fun getFollowers(): Followers? {
        return followers
    }

    fun setFollowers(followers: Followers?) {
        this.followers = followers
    }

    fun withFollowers(followers: Followers?): User? {
        this.followers = followers
        return this
    }

    fun getHref(): String? {
        return href
    }

    fun setHref(href: String?) {
        this.href = href
    }

    fun withHref(href: String?): User? {
        this.href = href
        return this
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun withId(id: String?): User? {
        this.id = id
        return this
    }

    fun getImages(): List<Image?>? {
        return images
    }

    fun setImages(images: List<Image?>?) {
        this.images = images
    }

    fun withImages(images: List<Image?>?): User? {
        this.images = images
        return this
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun withType(type: String?): User? {
        this.type = type
        return this
    }

    fun getUri(): String? {
        return uri
    }

    fun setUri(uri: String?) {
        this.uri = uri
    }

    fun withUri(uri: String?): User? {
        this.uri = uri
        return this
    }
}