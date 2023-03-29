package com.example.filmcase.model

import com.google.gson.annotations.SerializedName

data class DetailModel(

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
    @SerializedName("poster_path")
    val posterImage:String?,
    @SerializedName("genres")
    val genres:List<GenreModel>,
    @SerializedName("release_date")
    val years:String,
)
data class GenreModel(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,


)