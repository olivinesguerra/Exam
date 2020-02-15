package com.piquoti.myapplication.app.di.detail

import com.piquoti.myapplication.app.di.shared.AppModule
import com.piquoti.myapplication.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    fun providesViewModel(appModule : AppModule) =  DetailViewModel(appModule)
}