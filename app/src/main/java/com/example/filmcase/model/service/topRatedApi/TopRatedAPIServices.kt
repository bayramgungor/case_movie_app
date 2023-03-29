package com.example.filmcase.model.service.topRatedApi

import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.service.populerApi.PopulerAPI
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TopRatedAPIServices {

    private  val BASE_URL="https://api.themoviedb.org/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TopRatedAPI::class.java)

    fun getTopRatedData(): Single<FilmModel> {

        return api.getTopRatedFilm()

    }

}