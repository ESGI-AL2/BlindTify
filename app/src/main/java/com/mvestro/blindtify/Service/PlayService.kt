package com.mvestro.blindtify.Service

import com.mvestro.blindtify.Model.Playlist
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface PlayService {
    @Headers("Authorization: Bearer BQCHCNegB03uknlgGGFmNcxKqDAyVwujHwOWIeRX-gi5XHrW7rrrCS89t0dnBrVAm57Fxn1Oh8lxUpioS6sJ5an-KQ-_h5KaJbu2AqhT1W7xARl98O92HCHH5IbKZaItRX0W-250WnJuwDF-7BaOP429jlG0gfmbDAZwYiUqwQFeHzLAK1ChviIAA-iGdvOw4OJbGOoLlzUVqZLulVF6hFXoHzOGUmdzYeHiPIcIFZwoel5IoCAA_7BVlmVkMqIfDrWl-ngB82VJ755KZVQ")
    @PUT("/v1/me/player/play")
    fun play(): Call<ResponseBody>
}