package com.example.yameritoxmlcompose.ui.screens.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _nextEnable = MutableLiveData<Boolean>()
    val nextEnable: LiveData<Boolean> = _nextEnable

    fun onAddressChange(address: String) {
        _address.value = address
        _nextEnable.value = address.isNotEmpty()
    }
}