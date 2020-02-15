package com.piquoti.myapplication.ui.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginLeft
import org.jetbrains.anko.*

class ItunesItemRowUI : AnkoComponent<ViewGroup> {

    companion object {
        val ARTISTNAME = 10000
        val TRACKIMAGE = 10001
        val PRICE = 10002
        val GENRE = 10003
        val TRACKNAME = 10004
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        linearLayout {
            orientation = LinearLayout.VERTICAL

            imageView {
                id = TRACKIMAGE
            }.lparams {
                width = dip(100)
                height = dip(100)
                leftMargin = dip(10)
                topMargin = dip(10)
            }

            textView {
                id = ARTISTNAME
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }

            textView {
                id = TRACKNAME
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }


            textView {
                id = PRICE
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }


            textView {
                id = GENRE
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }
        }
    }
}