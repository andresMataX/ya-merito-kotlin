package com.example.yameritoxmlcompose.ui.screens.main.ui

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat

    private val _lon = MutableLiveData<Double>()
    val lon: LiveData<Double> = _lon

    fun onLocationChange(location: Location) {
        _lat.value = location.latitude
        _lon.value = location.longitude
    }

}