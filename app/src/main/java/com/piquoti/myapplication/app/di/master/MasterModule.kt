package com.piquoti.myapplication.app.di.master

import com.piquoti.myapplication.app.di.shared.AppModule
import com.piquoti.myapplication.app.provider.ItunesProvider
import com.piquoti.myapplication.viewmodel.MasterViewModel
import dagger.Module
import dagger.Provides


@Module
class MasterModule {

    @Provides
    fun providesViewModel(appModule : AppModule, itunesProvider: ItunesProvider) =  MasterViewModel(appModule, itunesProvider)
}