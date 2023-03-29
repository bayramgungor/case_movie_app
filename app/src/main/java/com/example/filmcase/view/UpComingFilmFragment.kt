package com.example.filmcase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmcase.R
import com.example.filmcase.adater.FilmAdapter
import com.example.filmcase.viewmodel.PopulerFilmViewModel
import com.example.filmcase.viewmodel.UpComingViewModel
import kotlinx.android.synthetic.main.now_play_film_fragment.*
import kotlinx.android.synthetic.main.populer_film_fragmnet.*
import kotlinx.android.synthetic.main.upcoming_film_fragment.*

class UpComingFilmFragment:Fragment() {
    private lateinit var viewModel: UpComingViewModel
    private val filmAdapter= FilmAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.upcoming_film_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel= ViewModelProviders.of(this).get(UpComingViewModel::class.java)
        viewModel.refreshData()
        upComingFilmList.layoutManager= LinearLayoutManager(context)
        upComingFilmList.adapter=filmAdapter

        upComingSwipeRefreshLayout.setOnRefreshListener {
            upComingFilmList.visibility=View.GONE
            upComingFilmError.visibility=View.GONE
            upComingFilmProgressBar.visibility=View.GONE
            viewModel.refreshData()
            upComingSwipeRefreshLayout.isRefreshing=false

        }

        observeLiveData()

    }
    fun observeLiveData(){

        viewModel.upComingFilms.observe(viewLifecycleOwner, Observer { upComingFilms->
            upComingFilms.let {
                upComingFilmList.visibility=View.VISIBLE
                filmAdapter.updateFilmList(upComingFilms.results)
            }

        })
        viewModel.upComingFilmsError.observe(viewLifecycleOwner, Observer { error->
            error.let {
                if(it){
                    upComingFilmError.visibility=View.VISIBLE

                }else{
                    upComingFilmError.visibility=View.GONE
                }
            }


        })
        viewModel.upComingFilmsLoading.observe(viewLifecycleOwner, Observer {loadig->
        loadig.let {
            if (it){
                upComingFilmProgressBar.visibility=View.VISIBLE
                upComingFilmList.visibility=View.GONE
                upComingFilmError.visibility=View.GONE
            }else{
                upComingFilmProgressBar.visibility=View.GONE
            }
        }

        })



    }
}