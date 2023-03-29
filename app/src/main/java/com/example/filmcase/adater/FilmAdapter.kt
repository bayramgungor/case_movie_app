package com.example.filmcase.adater

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.filmcase.R
import com.example.filmcase.model.FilmModel
import com.example.filmcase.model.Result
import com.example.filmcase.util.downloadFromUrl
import com.example.filmcase.util.placeholderProgressBar
import com.example.filmcase.view.DetailFragment
import com.example.filmcase.view.MainActivity
import kotlinx.android.synthetic.main.item_film.view.*


class FilmAdapter(val filmList:ArrayList<com.example.filmcase.model.Result>):RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    class FilmViewHolder (var view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
       val inflater =LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.item_film,parent,false)

        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {

        holder.view.nameTextView.text=filmList[position].filmName
        holder.view.tagline.text=filmList[position].filmShortDescription
        holder.view.imdb.text= filmList[position].puan.toString()
        holder.view.gösterim.text=filmList[position].izlenme.toString()
        holder.view.imageView.downloadFromUrl(filmList[position].imageUrl.toString(),
            placeholderProgressBar(holder.view.context)
        )
      /*  holder.view.setOnClickListener{
            findNavController(fragment = Fragment()).navigate(R.id.action_populerFilmFragment_to_detailFragment)

        }*/









    }

    override fun getItemCount(): Int {
        return filmList.size
    }
    fun updateFilmList(newFilmList: List<com.example.filmcase.model.Result>){
        filmList.clear()
        filmList.addAll(newFilmList)
        notifyDataSetChanged()
    }
}