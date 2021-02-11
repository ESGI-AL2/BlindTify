package com.mvestro.blindtify.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BuilderService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spotify.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}