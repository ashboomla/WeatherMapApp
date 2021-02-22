package com.example.weathermapappexercise.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weathermapappexercise.R
import com.example.weathermapappexercise.model.WeatherItem
import com.example.weathermapappexercise.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_details.*
import kotlin.math.roundToInt

class DetailsActivity : AppCompatActivity() {

    private lateinit var weatherItem: WeatherItem
    private lateinit var cityName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        handleIntent(intent)
        toolbarSetup()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.hasExtra(WeatherResponse.WEATHER_ITEM) == true) {
            weatherItem =
                (intent.getSerializableExtra(WeatherResponse.WEATHER_ITEM) as? WeatherItem)!!
            Log.d("RECEIVED weatherItem: ", weatherItem.toString())

            val mainTemp =
                weatherItem.main?.temp?.minus(273.15)?.times(1.8)?.plus(32)?.roundToInt() ?: 0
            val feelsLikeTemp =
                weatherItem.main?.feels_like?.minus(273.15)?.times(1.8)?.plus(32)?.roundToInt() ?: 0

            details_temperature_text.text = mainTemp.toString()
            details_feels_like_text.text =
                getString(R.string.label_feels_like) + feelsLikeTemp.toString()
            details_weather_main.text = weatherItem.weather?.get(0)?.main
            details_weather_description.text = weatherItem.weather?.get(0)?.description

        }
        if (intent?.hasExtra(WeatherResponse.CITY_TITLE) == true) {
            cityName = (intent.getStringExtra((WeatherResponse.CITY_TITLE)).toString())
        }

    }

    private fun toolbarSetup() {
        val actionBar = supportActionBar
        actionBar?.title = cityName
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}