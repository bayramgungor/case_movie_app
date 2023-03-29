package com.example.filmcase.model.service.topRatedApi

import com.example.filmcase.model.FilmModel
import io.reactivex.Single
import retrofit2.http.GET

interface TopRatedAPI {

    @GET("3/movie/top_rated?api_key=e9d65ceea231435973392507fdf8061e")
    fun getTopRatedFilm(): Single<FilmModel>
}