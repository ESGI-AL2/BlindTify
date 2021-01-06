package com.mvestro.blindtify.Model.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Followers {
    @SerializedName("total")
    @Expose
    private var total: Int? = null

    fun getTotal(): Int? {
        return total
    }

    fun setTotal(total: Int?) {
        this.total = total
    }

    fun withTotal(total: Int?): Followers? {
        this.total = total
        return this
    }
}