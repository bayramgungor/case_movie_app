package com.example.filmcase.model.service.upCommingApi

import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.service.topRatedApi.TopRatedAPI
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class UpCommingAPIServices {


    private  val BASE_URL="https://api.themoviedb.org/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpCommingAPI::class.java)

    fun getUpCominData(): Single<FilmModel> {

        return api.getUpCommingFilm()

    }



}