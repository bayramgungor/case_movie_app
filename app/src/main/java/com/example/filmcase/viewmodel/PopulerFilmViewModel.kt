package com.example.filmcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.Result
import com.example.filmcase.model.service.populerApi.PopulerAPIServices
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PopulerFilmViewModel :ViewModel() {
    private val populerAPIServices=PopulerAPIServices()
    private val disposable=CompositeDisposable()

    val populerFilms=MutableLiveData<FilmModel>()
    val populerFilsError=MutableLiveData<Boolean>()
    val populerFilmsLoading=MutableLiveData<Boolean>()



    fun refreshData(){

        getDataFromAPI()

    }
    fun getDataFromAPI(){
        populerFilmsLoading.value=true
        disposable.add(
            populerAPIServices.getPopulerData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<FilmModel>(){
                    override fun onSuccess(t: FilmModel) {
                        populerFilms.value=t
                        populerFilsError.value=false
                        populerFilmsLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        populerFilmsLoading.value=false
                        populerFilsError.value=true
                        e.printStackTrace()
                        print(e)
                    }

                })
        )

    }


}