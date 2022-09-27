package com.example.yameritoxmlcompose.ui.fragments

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yameritoxmlcompose.helpers.Distancia
import com.google.maps.android.compose.CameraPositionState

class LocalizationViewModel : ViewModel() {

    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat
    
    private val _lon = MutableLiveData<Double>()
    val lon: LiveData<Double> = _lon

    private val _camPos = MutableLiveData<CameraPositionState>()
    val camPos: LiveData<CameraPositionState> = _camPos

    private val _distancia = MutableLiveData<Double>()
    val distancia : LiveData<Double> = _distancia

    fun onChangeCoordenadas(lat1: Double, lon1: Double, lat2: Double, lon2: Double) {

        val radioTierra = 6378.137

        var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180
        var dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180

        var a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2)

        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

        var d = radioTierra * c

        _distancia.value = d * 1000
    }

    fun onChangeLat(latLocation: Double, lonLocation: Double) {
        _lat.value = latLocation
        _lon.value = lonLocation
    }
}