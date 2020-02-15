package com.piquoti.myapplication.app.di.master

import com.piquoti.myapplication.activity.MainActivity
import com.piquoti.myapplication.app.di.shared.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])

interface MasterComponent {
    fun inject(activity: MainActivity)
}