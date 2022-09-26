package com.example.yameritoxmlcompose.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.CameraPositionState

class LocalizationViewModel : ViewModel() {

    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat
    
    private val _lon = MutableLiveData<Double>()
    val lon: LiveData<Double> = _lon

    private val _camPos = MutableLiveData<CameraPositionState>()
    val camPos: LiveData<CameraPositionState> = _camPos

    fun onChangeLat(latLocation: Double, lonLocation: Double) {
        _lat.value = latLocation
        _lon.value = lonLocation
    }
}