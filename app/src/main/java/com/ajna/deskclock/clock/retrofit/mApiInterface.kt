package com.ajna.deskclock.clock.retrofit

import com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI.weatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface mApiInterface {

    @GET("data/2.5/weather/")
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") key: String
    ): Response<weatherData>

}