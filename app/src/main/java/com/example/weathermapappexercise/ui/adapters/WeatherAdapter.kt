package com.example.weathermapappexercise.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermapappexercise.R
import com.example.weathermapappexercise.model.WeatherItem
import com.example.weathermapappexercise.model.WeatherResponse
import com.example.weathermapappexercise.ui.activities.DetailsActivity
import kotlinx.android.synthetic.main.row_weather_adapter.view.*
import kotlin.math.roundToInt

class WeatherAdapter(
    private var wContext: Context,
    private var wResponse: WeatherResponse
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    val wList: List<WeatherItem>? = wResponse.list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_weather_adapter, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val wItem = wList?.get(position)
        if (wItem != null) {
            holder.bind(wItem, position)
        }
    }

    override fun getItemCount(): Int = wList?.size!!

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItem: WeatherItem, position: Int) {
            val temp =
                weatherItem.main?.temp?.minus(273.15)?.times(1.8)?.plus(32)?.roundToInt() ?: 0

            itemView.weather_main_text.text = weatherItem.weather?.get(0)?.main ?: ""
            itemView.temp_text.text = wContext.getString(R.string.label_temp) +temp.toString()
            itemView.setOnClickListener {
                val detailsIntent = Intent(wContext, DetailsActivity::class.java)
                detailsIntent.putExtra(WeatherResponse.WEATHER_ITEM, wList?.get(position))
                detailsIntent.putExtra(WeatherResponse.CITY_TITLE, wResponse.city?.name.toString())
                wContext.startActivity(detailsIntent)
            }
        }
    }

}