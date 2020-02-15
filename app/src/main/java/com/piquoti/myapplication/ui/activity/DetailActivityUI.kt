package com.piquoti.myapplication.ui.activity



import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import com.piquoti.myapplication.activity.DetailActivity
import com.piquoti.myapplication.ui.adapter.ItunesItemRowUI
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.linearLayoutCompat
import org.jetbrains.anko.appcompat.v7.searchView
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailActivityUI : AnkoComponent<DetailActivity>  {



    lateinit var artistTxtView : TextView
    lateinit var trackImage : ImageView
    lateinit var priceTxtView : TextView
    lateinit var genreTxtView : TextView
    lateinit var trackNameTxtView : TextView
    lateinit var longDescriptionTxtView : TextView

    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {

        linearLayoutCompat {
            orientation = LinearLayoutCompat.VERTICAL
            lparams(matchParent, matchParent)

            appBarLayout {

                toolbar {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 4f

                    relativeLayout {

                        textView {
                            text = "Detail"
                            textColor = Color.WHITE
                            textSize = 25.toFloat()
                            gravity = Gravity.CENTER_VERTICAL
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            width = matchParent
                            height = matchParent
                        }

                    }.lparams{
                        width = matchParent
                        height = matchParent
                    }

                }.lparams {
                    width = matchParent
                    height = dip(50)
                }
            }

            trackImage = imageView {

            }.lparams {
                width = dip(100)
                height = dip(100)
                leftMargin = dip(10)
                topMargin = dip(10)
            }

            artistTxtView = textView {
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }

            priceTxtView = textView {
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }


            genreTxtView = textView {
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }


            trackNameTxtView = textView {
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                bottomMargin = dip(10)
            }

            longDescriptionTxtView = textView {
                textColor = Color.BLACK
            }.lparams {
                width = wrapContent
                height = wrapContent
                leftMargin = dip(10)
                rightMargin = dip(10)
                bottomMargin = dip(10)
            }

        }
    }
}