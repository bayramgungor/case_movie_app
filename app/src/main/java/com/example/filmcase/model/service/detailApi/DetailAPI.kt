package com.example.filmcase.model.service.detailApi

import com.example.filmcase.model.DetailModel
import io.reactivex.Single
import retrofit2.http.GET

interface DetailAPI {

//https://api.themoviedb.org/3/movie/785084?api_key=e9d65ceea231435973392507fdf8061e
    @GET("3/movie/{id}?api_key=e9d65ceea231435973392507fdf8061e")

    fun getDetail(id:String):Single<DetailModel>

}