package com.example.weathermapappexercise.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weathermapappexercise.R
import com.example.weathermapappexercise.model.WeatherResponse
import com.example.weathermapappexercise.vm.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    private var weatherResponse: WeatherResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)


        observeResponses()

        main_button_lookup.setOnClickListener {
            viewModel.getWeatherByCity(main_edit_text_input.text.toString())
        }
    }

    override fun onDestroy() {
        viewModel.dispose()
        super.onDestroy()
    }

    private fun cityIntent() {
        val cityIntent = Intent(this, CityActivity::class.java)
        cityIntent.putExtra(WeatherResponse.WEATHER_BUNDLE, weatherResponse)
        startActivity(cityIntent)
    }

    private fun observeResponses() {
        viewModel.weatherResponse.observe(this, {
            weatherResponse = it as WeatherResponse

            Log.d("MainActivity", "weatherResponse Observed: $it")
            cityIntent()
        })

        viewModel.errorResponse.observe(this, {
            Toast.makeText(this, "Unable to process request: $it", Toast.LENGTH_SHORT).show()
        })

    }

}