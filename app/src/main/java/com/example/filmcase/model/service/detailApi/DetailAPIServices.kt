package com.example.filmcase.model.service.detailApi

import com.example.filmcase.model.DetailModel
import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.service.nowPlayApi.NowPlayAPI
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DetailAPIServices {

    private  val BASE_URL="https://api.themoviedb.org/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DetailAPI::class.java)

    fun getDetailData(id:String): Single<DetailModel> {

        return api.getDetail(id=id)

    }
}