package com.piquoti.myapplication.viewmodel

import com.piquoti.myapplication.app.di.shared.AppModule
import javax.inject.Inject

class DetailViewModel {

    var appModule: AppModule

    @Inject
    constructor(appModule : AppModule) {
        this@DetailViewModel.appModule = appModule
    }
}