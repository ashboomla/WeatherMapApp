package com.example.weathermapappexercise.model

import java.io.Serializable

data class WeatherResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<WeatherItem>?,
    val message: Int?
) : Serializable {
    companion object {
        const val WEATHER_BUNDLE = "weather_bundle"
        const val WEATHER_ITEM = "weather_item"
        const val CITY_TITLE = "city_title"
    }

}

data class City(
    val coord: Coord?,
    val country: String?,
    val id: Int?,
    val name: String?,
    val population: Int?,
    val sunrise: Int?,
    val sunset: Int?,
    val timezone: Int?
) : Serializable

data class WeatherItem(
    val clouds: Clouds?,
    val dt: Int?,
    val dt_txt: String?,
    val main: Main?,
    val pop: Double?,
    val rain: Rain?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<WeatherX>?,
    val wind: Wind?
) : Serializable

data class Coord(
    val lat: Double?,
    val lon: Double?
) : Serializable

data class Clouds(
    val all: Int?
) : Serializable

data class Main(
    val feels_like: Double?,
    val grnd_level: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val sea_level: Int?,
    val temp: Double?,  //temperature
    val temp_kf: Double?,
    val temp_max: Double?,
    val temp_min: Double?
) : Serializable

data class Rain(
    val `3h`: Double?
) : Serializable

data class Sys(
    val pod: String?
) : Serializable

data class WeatherX(
    val description: String?, //broken clouds
    val icon: String?,
    val id: Int?,
    val main: String?  // clear / cloudy / rainy
) : Serializable

data class Wind(
    val deg: Int?,
    val speed: Double?
) : Serializable