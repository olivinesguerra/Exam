package com.piquoti.myapplication.app.di

import android.app.Application
import com.piquoti.myapplication.app.di.shared.AppComponent
import com.piquoti.myapplication.app.di.shared.AppModule
import com.piquoti.myapplication.app.di.shared.DaggerAppComponent
import timber.log.Timber

class MainApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent() = appComponent
}