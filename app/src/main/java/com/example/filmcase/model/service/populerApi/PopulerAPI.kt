package com.example.filmcase.model.service.populerApi

import com.example.filmcase.model.FilmModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface PopulerAPI {


    //https://api.themoviedb.org/
//  3/movie/popular?api_key=e9d65ceea231435973392507fdf8061e
    @GET("3/movie/popular?api_key=e9d65ceea231435973392507fdf8061e")
    fun getPopulerFilm():Single<FilmModel>


}