package com.ajna.deskclock.clock.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object mRetrofitClient {
    private var client: OkHttpClient? = null
    private var retrofit: mApiInterface? = null

    fun getClient(context: Context?): mApiInterface? {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
            client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(ConnectivityInterceptor(context!!))
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(mApiInterface::class.java)
        }
        return retrofit
    }
}