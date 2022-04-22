package com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    var all: Int
)