package com.piquoti.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.kaopiz.kprogresshud.KProgressHUD
import com.piquoti.myapplication.R
import com.piquoti.myapplication.app.di.MainApplication
import com.piquoti.myapplication.app.model.ItunesItem
import com.piquoti.myapplication.ui.activity.DetailActivityUI
import com.piquoti.myapplication.viewmodel.DetailViewModel
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.setContentView
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    private val LOG_TAG = DetailActivity::class.java.simpleName
    private lateinit var ui : DetailActivityUI
    private var hud : KProgressHUD? = null
    private lateinit var compositeDisposable : CompositeDisposable

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = DetailActivityUI()
        ui.setContentView(this)

        (application as MainApplication).getAppComponent().inject(this)

        compositeDisposable = CompositeDisposable()

        val item = intent.getSerializableExtra("data") as? ItunesItem

        ui.artistTxtView.text = "Artist Name: " + item!!.artistName!!
        ui.priceTxtView.text = "Price: $" + item!!.trackPrice!! + " AUD"
        ui.genreTxtView.text = "Genre: " + item!!.primaryGenreName!!
        ui.trackNameTxtView.text = "Track Name " + item!!.trackName!!
        ui.longDescriptionTxtView.text = item!!.longDescription

        Glide
            .with(this)
            .load(item.artworkUrl100)
            .placeholder(R.drawable.ic_placeholder)
            .into(ui.trackImage)

        val gson = Gson()
        val json = gson.toJson(item)

        this@DetailActivity.viewModel.appModule.sharedPrefModule().lastItem = json

    }

    override fun onDestroy() {
        super.onDestroy()

        this@DetailActivity.viewModel.appModule.sharedPrefModule().lastItem = ""
    }
}
