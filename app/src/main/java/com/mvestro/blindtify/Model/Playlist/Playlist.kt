package com.mvestro.blindtify.Model.Playlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mvestro.blindtify.Model.User.User


class Playlist() {
    @SerializedName("href")
    @Expose
    private var href: String? = null

    @SerializedName("items")
    @Expose
    private var items: List<Item?>? = null

    @SerializedName("limit")
    @Expose
    private var limit: Int? = null

    @SerializedName("next")
    @Expose
    private var next: String? = null

    @SerializedName("offset")
    @Expose
    private var offset: Int? = null

    @SerializedName("previous")
    @Expose
    private var previous: Any? = null

    @SerializedName("total")
    @Expose
    private var total: Int? = null

    fun getHref(): String? {
        return href
    }

    fun setHref(href: String?) {
        this.href = href
    }

    fun withHref(href: String?): Playlist {
        this.href = href
        return this
    }

    fun getItems(): List<Item?>? {
        return items
    }

    fun setItems(items: List<Item?>?) {
        this.items = items
    }

    fun withItems(items: List<Item?>?): Playlist {
        this.items = items
        return this
    }

    fun getLimit(): Int? {
        return limit
    }

    fun setLimit(limit: Int?) {
        this.limit = limit
    }

    fun withLimit(limit: Int?): Playlist {
        this.limit = limit
        return this
    }

    fun getNext(): String? {
        return next
    }

    fun setNext(next: String?) {
        this.next = next
    }

    fun withNext(next: String?): Playlist {
        this.next = next
        return this
    }

    fun getOffset(): Int? {
        return offset
    }

    fun setOffset(offset: Int?) {
        this.offset = offset
    }

    fun withOffset(offset: Int?): Playlist {
        this.offset = offset
        return this
    }

    fun getPrevious(): Any? {
        return previous
    }

    fun setPrevious(previous: Any?) {
        this.previous = previous
    }

    fun withPrevious(previous: Any?): Playlist {
        this.previous = previous
        return this
    }

    fun getTotal(): Int? {
        return total
    }

    fun setTotal(total: Int?) {
        this.total = total
    }

    fun withTotal(total: Int?): Playlist {
        this.total = total
        return this
    }
}