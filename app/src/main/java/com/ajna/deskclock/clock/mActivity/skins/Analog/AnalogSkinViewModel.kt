package com.ajna.deskclock.clock.mActivity.skins.Analog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajna.deskclock.clock.Respo
import com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI.weatherData
import com.ajna.deskclock.clock.retrofit.Resource

class AnalogSkinViewModel(private val respo: Respo) : ViewModel() {
    suspend fun weather(): Resource<weatherData> {
        return respo.getWeather()
    }

    suspend fun hours(): String {
        return respo.getHours()
    }

    suspend fun seconds(): String {
        return respo.getSeconds()
    }

    suspend fun minutes(): String {
        return respo.getMinutes()
    }

    suspend fun date(): String {
        return respo.getDate()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Category Fragment"
    }
    val text: LiveData<String> = _text
}