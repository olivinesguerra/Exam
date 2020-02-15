package com.piquoti.myapplication.app.api

import com.piquoti.myapplication.app.model.ItunesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("/search?amp;country=au&amp;media=movie&amp;all")
    fun searchItem(@Query("term" , encoded = true) term : String ) : Observable<ItunesResponse>
}