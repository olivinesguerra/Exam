package com.piquoti.myapplication.app.model

import java.io.Serializable


data class ItunesItem (
    var artistName : String? = "",
    var trackName : String? = "",
    var artistViewUrl : String? = "",
    var trackViewUrl : String? = "",
    var artworkUrl30 : String? = "",
    var artworkUrl60 : String? = "",
    var artworkUrl100 : String? = "",
    var trackPrice: Float? = 0.00F,
    var primaryGenreName: String? = "",
    var longDescription: String? = ""
) : Serializable