package com.piquoti.myapplication.viewmodel

import android.util.Log
import com.piquoti.myapplication.app.di.shared.AppModule
import com.piquoti.myapplication.app.model.ItunesItem
import com.piquoti.myapplication.app.provider.ItunesProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MasterViewModel {

    private val LOG_TAG = MasterViewModel::class.java.simpleName

    private var compositeDisposable : CompositeDisposable

    private var itunesProvider: ItunesProvider
    var appModule: AppModule

    var isRequesting : BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    var queryString : BehaviorSubject<String> = BehaviorSubject.createDefault("")

    var list: MutableList<ItunesItem> = ArrayList()

    @Inject
    constructor(appModule : AppModule, itunesProvider: ItunesProvider) {

        this@MasterViewModel.itunesProvider = itunesProvider
        this@MasterViewModel.appModule = appModule

        compositeDisposable = CompositeDisposable()

        this@MasterViewModel.compositeDisposable.add(
            this.queryString
                .filter({it != ""})
                .flatMap { query ->
                    return@flatMap this@MasterViewModel.searchItem(query)
                }
                .subscribeOn(Schedulers.newThread())
                .subscribe { value ->

                }
        )
    }

    fun dispose(){
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    fun initDisposable (){
        compositeDisposable = CompositeDisposable()
    }

    private fun searchItem(query:String): Observable<Boolean> {
        isRequesting.onNext(true)

        return this.itunesProvider.searchItem(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->

                Log.i(LOG_TAG, result.resultCount.toString())
                list.clear()
                list.addAll(result.results!!)
                isRequesting.onNext(false)

                return@map false
            }
            .onErrorReturn {error ->
                Log.i(LOG_TAG, error.localizedMessage)
                isRequesting.onNext(false)
                return@onErrorReturn false
            }

    }
}