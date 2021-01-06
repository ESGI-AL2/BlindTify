package com.mvestro.blindtify.Model.Tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tracks {

    @SerializedName("href")
    @Expose
    private var href: String? = null

    @SerializedName("total")
    @Expose
    private var total: Int? = null

    fun getHref(): String? {
        return href
    }

    fun setHref(href: String?) {
        this.href = href
    }

    fun withHref(href: String?): Tracks? {
        this.href = href
        return this
    }

    fun getTotal(): Int? {
        return total
    }

    fun setTotal(total: Int?) {
        this.total = total
    }

    fun withTotal(total: Int?): Tracks? {
        this.total = total
        return this
    }

}