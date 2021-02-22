package com.example.weathermapappexercise.network

import com.example.weathermapappexercise.Config
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {

    val _api = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApiInterface::class.java)

    fun getWeatherApi(): WeatherApiInterface = _api

}