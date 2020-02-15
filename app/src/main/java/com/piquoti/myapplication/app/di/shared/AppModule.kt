package com.piquoti.myapplication.app.di.shared

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Module
class AppModule @Inject constructor(private val context: Context) {

    @Provides
    @NonNull
    fun provideContext() = context

    @Provides
    @NonNull
    fun sharedPrefModule() = SharePrefModule(context)

}