package local.kas.material.repository

import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayApi {
    @GET("/planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String)
}