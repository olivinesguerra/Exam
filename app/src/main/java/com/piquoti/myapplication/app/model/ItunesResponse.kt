package com.piquoti.myapplication.app.model


data class ItunesResponse (
    var resultCount : Int? = 0,
    var results: List<ItunesItem>? = null
)