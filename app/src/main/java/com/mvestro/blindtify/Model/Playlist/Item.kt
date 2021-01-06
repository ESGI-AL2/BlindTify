package com.mvestro.blindtify.Model.Playlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.mvestro.blindtify.Model.Tracks.Tracks

class Item {

    @SerializedName("collaborative")
    @Expose
    private var collaborative: Boolean? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("external_urls")
    @Expose
    private var externalUrls: ExternalUrls? = null

    @SerializedName("href")
    @Expose
    private var href: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("images")
    @Expose
    private var images: List<Image?>? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("owner")
    @Expose
    private var owner: Owner? = null

    @SerializedName("primary_color")
    @Expose
    private var primaryColor: Any? = null

    @SerializedName("public")
    @Expose
    private var _public: Boolean? = null

    @SerializedName("snapshot_id")
    @Expose
    private var snapshotId: String? = null

    @SerializedName("tracks")
    @Expose
    private var tracks: Tracks? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("uri")
    @Expose
    private var uri: String? = null

    fun getCollaborative(): Boolean? {
        return collaborative
    }

    fun setCollaborative(collaborative: Boolean?) {
        this.collaborative = collaborative
    }

    fun withCollaborative(collaborative: Boolean?): Item? {
        this.collaborative = collaborative
        return this
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun withDescription(description: String?): Item? {
        this.description = description
        return this
    }

    fun getExternalUrls(): ExternalUrls? {
        return externalUrls
    }

    fun setExternalUrls(externalUrls: ExternalUrls?) {
        this.externalUrls = externalUrls
    }

    fun withExternalUrls(externalUrls: ExternalUrls?): Item? {
        this.externalUrls = externalUrls
        return this
    }

    fun getHref(): String? {
        return href
    }

    fun setHref(href: String?) {
        this.href = href
    }

    fun withHref(href: String?): Item? {
        this.href = href
        return this
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun withId(id: String?): Item? {
        this.id = id
        return this
    }

    fun getImages(): List<Image?>? {
        return images
    }

    fun setImages(images: List<Image?>?) {
        this.images = images
    }

    fun withImages(images: List<Image?>?): Item? {
        this.images = images
        return this
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun withName(name: String?): Item? {
        this.name = name
        return this
    }

    fun getOwner(): Owner? {
        return owner
    }

    fun setOwner(owner: Owner?) {
        this.owner = owner
    }

    fun withOwner(owner: Owner?): Item? {
        this.owner = owner
        return this
    }

    fun getPrimaryColor(): Any? {
        return primaryColor
    }

    fun setPrimaryColor(primaryColor: Any?) {
        this.primaryColor = primaryColor
    }

    fun withPrimaryColor(primaryColor: Any?): Item? {
        this.primaryColor = primaryColor
        return this
    }

    fun getPublic(): Boolean? {
        return _public
    }

    fun setPublic(_public: Boolean?) {
        this._public = _public
    }

    fun withPublic(_public: Boolean?): Item? {
        this._public = _public
        return this
    }

    fun getSnapshotId(): String? {
        return snapshotId
    }

    fun setSnapshotId(snapshotId: String?) {
        this.snapshotId = snapshotId
    }

    fun withSnapshotId(snapshotId: String?): Item? {
        this.snapshotId = snapshotId
        return this
    }

    fun getTracks(): Tracks? {
        return tracks
    }

    fun setTracks(tracks: Tracks?) {
        this.tracks = tracks
    }

    fun withTracks(tracks: Tracks?): Item? {
        this.tracks = tracks
        return this
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun withType(type: String?): Item? {
        this.type = type
        return this
    }

    fun getUri(): String? {
        return uri
    }

    fun setUri(uri: String?) {
        this.uri = uri
    }

    fun withUri(uri: String?): Item? {
        this.uri = uri
        return this
    }

}