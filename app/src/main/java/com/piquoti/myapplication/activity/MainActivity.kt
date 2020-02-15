package com.piquoti.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.kaopiz.kprogresshud.KProgressHUD
import com.piquoti.myapplication.adapter.MainActivityAdapter
import com.piquoti.myapplication.app.di.MainApplication
import com.piquoti.myapplication.app.model.ItunesItem
import com.piquoti.myapplication.app.util.RxBus
import com.piquoti.myapplication.ui.activity.MainActivityUI
import com.piquoti.myapplication.viewmodel.MasterViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.setContentView
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    private lateinit var ui : MainActivityUI
    private var hud : KProgressHUD? = null
    private lateinit var compositeDisposable : CompositeDisposable
    private lateinit var adapter : MainActivityAdapter

    @Inject
    @Singleton
    lateinit var viewModel: MasterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = MainActivityUI()
        ui.setContentView(this)


        compositeDisposable = CompositeDisposable()

        (application as MainApplication).getAppComponent().inject(this)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        this@MainActivity.viewModel.appModule.sharedPrefModule().lastLogin = currentDate

        initialize()
    }

    override fun onStart() {
        super.onStart()

        compositeDisposable = CompositeDisposable()
        this@MainActivity.viewModel.initDisposable()
    }

    override fun onResume() {
        super.onResume()

        compositeDisposable = CompositeDisposable()
        this@MainActivity.viewModel.initDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()


        compositeDisposable.clear()
        compositeDisposable.dispose()
        this@MainActivity.viewModel.dispose()
    }

    override fun onPause() {
        super.onPause()

        compositeDisposable.clear()
        compositeDisposable.dispose()
        this@MainActivity.viewModel.dispose()
    }

    private fun showDialog(){
        this@MainActivity.hud = KProgressHUD.create(this@MainActivity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setCancellable(false)
            .setAnimationSpeed(2)
            .show()
    }

    private fun dismissDialog(){
        if (this@MainActivity.hud != null) {
            this@MainActivity.hud!!.dismiss()
        }
    }

    private fun initialize(){

        ui.recycleView.setHasFixedSize(false)
        ui.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        this@MainActivity.adapter = MainActivityAdapter(this.viewModel.list,this)
        ui.recycleView.adapter = this@MainActivity.adapter

        ui.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                this@MainActivity.viewModel.queryString.onNext(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        this@MainActivity.compositeDisposable.add(
            this.viewModel.isRequesting
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isRequesting ->
                    if (isRequesting) {
                        this@MainActivity.showDialog()
                    }else{
                       this@MainActivity.dismissDialog()
                    }

                    ui.recycleView.adapter!!.notifyDataSetChanged()
                }
        )

        this@MainActivity.compositeDisposable.add(
            RxBus.listen(ItunesItem::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { item ->
                    val intent = Intent(this@MainActivity,DetailActivity::class.java)
                    intent.putExtra("data", item)
                    startActivity(intent)
                }
        )

        if(this@MainActivity.viewModel.appModule.sharedPrefModule().lastItem != ""){
            val gson = Gson()
            val lastItem = gson.fromJson(this@MainActivity.viewModel.appModule.sharedPrefModule().lastItem, ItunesItem::class.java)
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("data", lastItem)
            startActivity(intent)
        }
    }
}
