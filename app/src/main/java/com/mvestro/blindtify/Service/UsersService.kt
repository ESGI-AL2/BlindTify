package com.mvestro.blindtify.Service

import com.mvestro.blindtify.Model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UsersService {

    @Headers("Authorization: Bearer BQAluADQm5wYjGp7UIHvUXD6mGJDzJhMTGcUXw7nNrSH_3BSXcM6Mqjf7TTNYpOD8Fyc7hQTY1bzVaB1pv16ObX5IWsZidhZYIQ-hwA63Rog8f4nk6XKuxb1ZcsdHJJx3O-ugzJ3aZQ8LaSomaErsoO0DxYUCyHoLNaZDshjI1VTSAsTf4VolchbsO3T4NdcfPcWKFQykhumNLYJc9C93zRkYN09CBuLD64P-fNaS2Pa2WEVL4PMZpxrum2XBcSkIUQNyFjtyMJHPvnK87k")
    @GET("/v1/me")
    fun listRepos(): Call<List<Playlist>>



/*    @Headers("Authorization: Bearer BQBh6oqC_D3jfPz_rZnkTL4GE0Eo7zzuSoFGe-OCnt0mEYMiO8CwkLkYaj7wSSOipeZcmlFpSuI9RLXA1atfQcqLXsFzcNidT0Q1LkRKwS_FXkThTIK87T9fYUNYpOQ21P7txWWYQEHRTS6-tQ")
    @GET("/v1/me")
    fun getUsers(): Call<List<Users>>*/
}