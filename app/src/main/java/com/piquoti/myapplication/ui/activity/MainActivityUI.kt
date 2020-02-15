package com.piquoti.myapplication.ui.activity


import android.os.Build
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.piquoti.myapplication.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.linearLayoutCompat
import org.jetbrains.anko.appcompat.v7.searchView
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI : AnkoComponent<MainActivity>  {

    lateinit var searchView : SearchView
    lateinit var toolbar : Toolbar
    lateinit var recycleView : RecyclerView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        linearLayoutCompat {
            orientation = LinearLayoutCompat.VERTICAL
            lparams(matchParent, matchParent)

            appBarLayout {1

                toolbar = toolbar {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 4f

                    relativeLayout {

                        searchView = searchView {

                        }.lparams {
                            width = matchParent
                            height = dip(50)
                        }

                    }.lparams{
                        width = matchParent
                        height = matchParent
                    }

                }.lparams {
                    width = matchParent
                    height = matchParent
                }
            }

            recycleView = recyclerView {

            }.lparams{
                width = matchParent
                height = matchParent
            }
        }
    }
}