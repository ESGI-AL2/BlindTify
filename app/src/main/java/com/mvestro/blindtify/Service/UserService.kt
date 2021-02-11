package com.mvestro.blindtify.Service

import com.mvestro.blindtify.Model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("/v1/me")
    fun getUser(@Header("Authorization") TOKEN: String): Call<User>

}