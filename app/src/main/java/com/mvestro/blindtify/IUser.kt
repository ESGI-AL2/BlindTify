package com.mvestro.blindtify

import com.mvestro.blindtify.User
import retrofit2.Call
import retrofit2.http.GET

interface IUser {
    @GET("User.json")
    fun getUser(): Call<User>
}