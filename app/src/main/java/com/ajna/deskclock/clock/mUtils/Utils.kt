package com.ajna.deskclock.clock.mUtils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

object Utils {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun mLog(message: String) {
        Log.d("SANJAY", "--------------------------------------")
        Log.d("SANJAY", "mLog: ")
        Log.d("SANJAY", "--------------------------------------")
    }


    fun degreeToFahrenheit(fahrenheit: Double): Int {
        return (fahrenheit - 273.15).roundToInt()
    }

    fun mToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun openGpsIfOff(context: Context) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            val service = context.getSystemService(Context.LOCATION_SERVICE)
            var enabled = false
            if (service is LocationManager) enabled =
                service.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!enabled) {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
                return
            }
        }
    }
}