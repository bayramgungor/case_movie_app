package com.example.filmcase.model

import com.google.gson.annotations.SerializedName


data class FilmModel(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Result>,
    @SerializedName("total_pages")
    val total_pages : Int,
    @SerializedName("total_results")
    val total_results : Int

)

data class Result(
    @SerializedName("title")
    val filmName: String?,
    @SerializedName("original_title")
    val filmShortDescription: String?,
    @SerializedName("vote_average")
    val puan: Double?,
    @SerializedName("backdrop_path")
    val imageUrl: String?,
    @SerializedName("vote_count")
    val izlenme: Int?,
    @SerializedName("release_date")
    val years:String,

)