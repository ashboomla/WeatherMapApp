package com.example.weathermapappexercise.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weathermapappexercise.model.WeatherResponse
import com.example.weathermapappexercise.network.WeatherApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository(WeatherApiClient)
    private var weatherDisposable = CompositeDisposable()
    var weatherResponse = MutableLiveData<WeatherResponse>()
    var errorResponse = MutableLiveData<String>()

    fun dispose() {
        weatherDisposable.dispose()
    }

    fun getWeatherByCity(city: String) {
        repository.getWeatherByCity(city)
        weatherDisposable.add(
            repository.getWeatherByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherResponse.postValue(it)
                }, {
                    Log.e("Error ", "${it.message}")
                    errorResponse.postValue( it.message.toString())
                })
        )
    }
}
