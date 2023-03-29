package com.example.filmcase.model.service.upCommingApi

import com.example.filmcase.model.FilmModel
import io.reactivex.Single
import retrofit2.http.GET

interface UpCommingAPI {


    @GET("3/movie/upcoming?api_key=e9d65ceea231435973392507fdf8061e")
    fun getUpCommingFilm():Single<FilmModel>
}