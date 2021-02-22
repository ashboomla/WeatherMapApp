package com.example.weathermapappexercise.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermapappexercise.R
import com.example.weathermapappexercise.model.WeatherResponse
import com.example.weathermapappexercise.ui.adapters.WeatherAdapter
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity : AppCompatActivity() {
    private lateinit var weatherResponse: WeatherResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        handleIntent(intent)
        toolbarSetup()
        val adapterRecyclerView = WeatherAdapter(this, weatherResponse)
        city_weather_recycler_view.adapter = adapterRecyclerView
        city_weather_recycler_view.layoutManager = LinearLayoutManager(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun toolbarSetup() {
        val actionBar = supportActionBar
        actionBar?.title = weatherResponse.city?.name ?: ""
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.hasExtra(WeatherResponse.WEATHER_BUNDLE) == true) {
            weatherResponse =
                (intent.getSerializableExtra(WeatherResponse.WEATHER_BUNDLE) as? WeatherResponse)!!
            Log.d("RECEIVED wResponse: ", weatherResponse.toString())
        }
    }
}