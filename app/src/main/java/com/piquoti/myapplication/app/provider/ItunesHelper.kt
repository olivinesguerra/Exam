package com.piquoti.myapplication.app.provider

import com.piquoti.myapplication.app.api.Client
import com.piquoti.myapplication.app.model.ItunesResponse
import io.reactivex.Observable
import javax.inject.Inject

class ItunesProvider  @Inject constructor() {

    fun searchItem(query:String) : Observable<ItunesResponse> {
        return Client.create().searchItem(query)
    }
}