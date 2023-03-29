package com.example.filmcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmcase.model.DetailModel
import com.example.filmcase.model.FilmModel

class DetailViewModel:ViewModel() {

    val detailLiveData=MutableLiveData<DetailModel>()



}