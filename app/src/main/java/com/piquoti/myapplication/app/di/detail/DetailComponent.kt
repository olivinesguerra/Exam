package com.piquoti.myapplication.app.di.detail

import com.piquoti.myapplication.activity.DetailActivity
import com.piquoti.myapplication.app.di.shared.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface DetailComponent {
    fun inject(activity: DetailActivity)
}