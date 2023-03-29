package com.example.filmcase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmcase.R
import com.example.filmcase.adater.FilmAdapter
import com.example.filmcase.viewmodel.PopulerFilmViewModel
import kotlinx.android.synthetic.main.now_play_film_fragment.*
import kotlinx.android.synthetic.main.populer_film_fragmnet.*
import kotlinx.android.synthetic.main.upcoming_film_fragment.*

class PopulerFilmFragment:Fragment() {
    private lateinit var viewModel:PopulerFilmViewModel
    private val filmAdapter=FilmAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.populer_film_fragmnet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(PopulerFilmViewModel::class.java)
        viewModel.refreshData()
        populerFilmList.layoutManager=LinearLayoutManager(context)
        populerFilmList.adapter=filmAdapter

        populerSwipeRefreshLayout.setOnRefreshListener {
            populerFilmList.visibility=View.GONE
            populerFilmError.visibility=View.GONE
            populerFilmProgressBar.visibility=View.GONE
            viewModel.refreshData()
            populerSwipeRefreshLayout.isRefreshing=false

        }

         observeLiveData()

    }
    fun observeLiveData(){
        viewModel.populerFilms.observe(viewLifecycleOwner, Observer { populerFilms->


            populerFilms.let {
                populerFilmList.visibility=View.VISIBLE
                 filmAdapter.updateFilmList(populerFilms.results)

            }

        })

        viewModel.populerFilsError.observe(viewLifecycleOwner, Observer { error->
            error.let {
                if(it){
                    populerFilmError.visibility=View.VISIBLE

                }else{
                    populerFilmError.visibility=View.GONE
                }
            }
        })
        viewModel.populerFilmsLoading.observe(viewLifecycleOwner, Observer { loading->

            loading.let {
                if (it){
                    populerFilmProgressBar.visibility=View.VISIBLE
                    populerFilmList.visibility=View.GONE
                    populerFilmError.visibility=View.GONE
                }else{
                    populerFilmProgressBar.visibility=View.GONE
                }

            }
        })
    }


}