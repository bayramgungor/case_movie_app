package com.example.filmcase.model.service.nowPlayApi

import com.example.filmcase.model.FilmModel
import io.reactivex.Single
import retrofit2.http.GET

interface NowPlayAPI {
    @GET("3/movie/now_playing?api_key=e9d65ceea231435973392507fdf8061e")

    fun getNowPlayFilm(): Single<FilmModel>
}