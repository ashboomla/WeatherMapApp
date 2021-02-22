package com.example.weathermapappexercise.network

import com.example.weathermapappexercise.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("forecast")
    fun getWeatherByCity(@Query("q") city: String, @Query("appid") key: String)
            : Single<WeatherResponse>

}
