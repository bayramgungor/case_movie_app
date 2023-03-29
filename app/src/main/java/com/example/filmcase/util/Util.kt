package com.example.filmcase.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmcase.R


fun ImageView.downloadFromUrl(url:String,progressDrawable:CircularProgressDrawable){
val IMAGE_URL="https://image.tmdb.org/t/p/original"
    val imageUrl=IMAGE_URL+url
    val options=RequestOptions()
        .placeholder( progressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imageUrl)
        .into(this)
}

fun placeholderProgressBar(contex:Context):CircularProgressDrawable{

    return CircularProgressDrawable(contex).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}