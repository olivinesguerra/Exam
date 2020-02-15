package com.piquoti.myapplication.app.di.shared

import android.content.Context
import android.preference.PreferenceManager
import com.piquoti.myapplication.app.util.SharePrefKey

class SharePrefModule constructor(val context : Context) {

    companion object {
        val DEVELOP_MODE = false
        private val DEVICE_TOKEN = "data.source.prefs.sharedPref"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var lastLogin = preferences.getString(SharePrefKey.LASTLOGIN, "")
        set(value) = preferences.edit().putString(SharePrefKey.LASTLOGIN,value).apply()

    var lastItem = preferences.getString(SharePrefKey.LASTITEM, "")
        set(value) = preferences.edit().putString(SharePrefKey.LASTITEM,value).apply()

    fun reset(){

    }
}