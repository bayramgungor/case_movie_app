package com.example.filmcase.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.service.populerApi.PopulerAPIServices
import com.example.filmcase.model.service.upCommingApi.UpCommingAPIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UpComingViewModel:ViewModel() {

    private val upComingAPIServices= UpCommingAPIServices()
    private val disposable= CompositeDisposable()

    val upComingFilms= MutableLiveData<FilmModel>()
    val upComingFilmsError= MutableLiveData<Boolean>()
    val upComingFilmsLoading= MutableLiveData<Boolean>()


    fun refreshData(){

        getDataFromAPI()

    }
    fun getDataFromAPI(){
        upComingFilmsLoading.value=true
        disposable.add(
            upComingAPIServices.getUpCominData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FilmModel>(){
                    override fun onSuccess(t: FilmModel) {
                        upComingFilms.value=t
                        upComingFilmsError.value=false
                        upComingFilmsLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        upComingFilmsLoading.value=false
                        upComingFilmsError.value=true
                        e.printStackTrace()
                        print(e)
                    }

                })
        )

    }

}