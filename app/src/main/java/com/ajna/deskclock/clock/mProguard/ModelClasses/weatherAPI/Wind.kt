package com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
)