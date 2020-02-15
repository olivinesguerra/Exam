package com.piquoti.myapplication.app.di.shared

import com.piquoti.myapplication.activity.DetailActivity
import com.piquoti.myapplication.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RestModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}