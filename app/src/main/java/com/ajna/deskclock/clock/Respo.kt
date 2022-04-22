package com.ajna.deskclock.clock

import com.ajna.deskclock.clock.mUtils.GPSTracker
import com.ajna.deskclock.clock.retrofit.BaseDataSource
import com.ajna.deskclock.clock.retrofit.mApiInterface
import java.text.SimpleDateFormat
import java.util.*


class Respo(
    private val apiInterface: mApiInterface,
    private val gps: GPSTracker,
) : BaseDataSource() {
    suspend fun getWeather() = getResult {
        apiInterface.getWeather(
            gps.latitude.toString(),
            gps.longitude.toString(),
            "2e3e87023a31a19d056c76e35a48a178")
    }


    suspend fun getTime(): String {
        val sdf = SimpleDateFormat("hh : mm")
        return sdf.format(Date())
    }
    suspend fun getFullTime(): String {
        val sdf = SimpleDateFormat("hh : mm : ss")
        return sdf.format(Date())
    }

    suspend fun getDate(): String {
        val sdf = SimpleDateFormat("E, dd MMM yyyy")
        return sdf.format(Date())
    }

    suspend fun getHours(): String {
        val sdf = SimpleDateFormat("hh")
        return sdf.format(Date())
    }

    suspend fun getMinutes(): String {
        val sdf = SimpleDateFormat("mm")
        return sdf.format(Date())
    }

    suspend fun getSeconds(): String {
        val sdf = SimpleDateFormat("ss")
        return sdf.format(Date())
    }

}

