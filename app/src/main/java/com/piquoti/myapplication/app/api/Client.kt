package com.piquoti.myapplication.app.api

import com.piquoti.myapplication.BuildConfig
import com.piquoti.myapplication.app.util.Constants
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


class Client {
    companion object {

        fun create(): ItunesApi {

            val moshi = Moshi.Builder().build()

            val client = OkHttpClient
                .Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)


            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) })
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            client.addInterceptor(interceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create(moshi)
                )
                .client(client.build())
                .build()

            return retrofit.create(ItunesApi::class.java)
        }
    }
}