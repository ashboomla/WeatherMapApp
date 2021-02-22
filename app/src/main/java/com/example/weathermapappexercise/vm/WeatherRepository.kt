package com.example.weathermapappexercise.vm

import com.example.weathermapappexercise.Config
import com.example.weathermapappexercise.model.WeatherResponse
import com.example.weathermapappexercise.network.WeatherApiClient
import io.reactivex.Single

class WeatherRepository(private val api: WeatherApiClient) {

    fun getWeatherByCity(city: String): Single<WeatherResponse> =
        api.getWeatherApi().getWeatherByCity(city, Config.API_KEY)

}
