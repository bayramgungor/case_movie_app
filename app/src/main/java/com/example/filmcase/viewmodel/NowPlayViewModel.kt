package com.example.filmcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.service.nowPlayApi.NowPlayAPIServices
import com.example.filmcase.model.service.populerApi.PopulerAPIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NowPlayViewModel:ViewModel() {
    private val nowPlayAPIServices= NowPlayAPIServices()
    private val disposable= CompositeDisposable()

    val nowPlayFilms= MutableLiveData<FilmModel>()
    val nowPlayFilsError= MutableLiveData<Boolean>()
    val nowPlayFilmsLoading= MutableLiveData<Boolean>()


    fun refreshData(){

        getDataFromAPI()

    }
    fun getDataFromAPI(){
        nowPlayFilmsLoading.value=true
        disposable.add(
            nowPlayAPIServices.getNowPlayData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FilmModel>(){
                    override fun onSuccess(t: FilmModel) {
                        nowPlayFilms.value=t
                        nowPlayFilsError.value=false
                        nowPlayFilmsLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        nowPlayFilmsLoading.value=false
                        nowPlayFilsError.value=true
                        e.printStackTrace()
                        print(e)
                    }

                })
        )

    }
}