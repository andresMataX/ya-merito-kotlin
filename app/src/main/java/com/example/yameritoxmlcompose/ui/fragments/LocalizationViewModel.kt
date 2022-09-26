package com.example.yameritoxmlcompose.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocalizationViewModel : ViewModel() {

    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat

    fun onChangeLat(latLocation: Double) {
        _lat.value = latLocation
    }
}