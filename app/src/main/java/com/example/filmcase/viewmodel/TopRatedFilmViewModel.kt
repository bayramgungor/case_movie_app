package com.example.filmcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmcase.model.FilmModel

import com.example.filmcase.model.service.topRatedApi.TopRatedAPIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TopRatedFilmViewModel:ViewModel() {
    private val topRatedAPIServices= TopRatedAPIServices()
    private val disposable= CompositeDisposable()

    val topRatedFilms= MutableLiveData<FilmModel>()
    val topRatedFilmsError= MutableLiveData<Boolean>()
    val topRatedFilmsLoading= MutableLiveData<Boolean>()

    fun refreshData(){

        getDataFromAPI()

    }



    fun getDataFromAPI(){
        topRatedFilmsLoading.value=true
        disposable.add(
            topRatedAPIServices.getTopRatedData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FilmModel>(){
                    override fun onSuccess(t: FilmModel) {
                        topRatedFilms.value=t
                        topRatedFilmsError.value=false
                        topRatedFilmsLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        topRatedFilmsLoading.value=false
                        topRatedFilmsError.value=true
                        e.printStackTrace()
                        print(e)
                    }

                })
        )
}
}