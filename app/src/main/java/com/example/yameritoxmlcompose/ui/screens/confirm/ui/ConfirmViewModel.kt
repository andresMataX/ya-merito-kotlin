package com.example.yameritoxmlcompose.ui.screens.confirm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmViewModel: ViewModel() {

    private val _range = MutableLiveData<Int>()
    val range: LiveData<Int> = _range

    private val _send = MutableLiveData<Boolean>()
    val send: LiveData<Boolean> = _send

    fun onRangeChange(range: Int) {
        _range.value = range
    }

    fun onSendRange(send: Boolean) {
        _send.value = send
    }
}