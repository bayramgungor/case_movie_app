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
import com.example.filmcase.viewmodel.TopRatedFilmViewModel
import kotlinx.android.synthetic.main.now_play_film_fragment.*
import kotlinx.android.synthetic.main.top_rated_film_fragment.*
import kotlinx.android.synthetic.main.upcoming_film_fragment.*

class TopRatedFilmFragment:Fragment() {

    private lateinit var viewModel: TopRatedFilmViewModel
    private val filmAdapter= FilmAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.top_rated_film_fragment, container, false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(TopRatedFilmViewModel::class.java)
        viewModel.refreshData()
        topRatedFilmList.layoutManager= LinearLayoutManager(context)
        topRatedFilmList.adapter=filmAdapter

        topRatedSwipeRefreshLayout.setOnRefreshListener {
            topRatedFilmList.visibility=View.GONE
            topRatedFilmError.visibility=View.GONE
            topRatedFilmProgressBar.visibility=View.GONE
            viewModel.refreshData()
            topRatedSwipeRefreshLayout.isRefreshing=false

        }

        observerLiveData()
    }


        fun observerLiveData(){
            viewModel.topRatedFilms.observe(viewLifecycleOwner, Observer{topRatedFilms->

                topRatedFilms.let {
                    topRatedFilmList.visibility=View.VISIBLE
                    filmAdapter.updateFilmList(topRatedFilms.results)
                }

            })

            viewModel.topRatedFilmsError.observe(viewLifecycleOwner, Observer { error->

                error.let {
                    if(it){
                        topRatedFilmError.visibility=View.VISIBLE

                    }else{
                        topRatedFilmError.visibility=View.GONE
                    }

                }
            })
            viewModel.topRatedFilmsLoading.observe(viewLifecycleOwner, Observer { loading->
                loading.let {

                    if (it){
                        topRatedFilmProgressBar.visibility=View.VISIBLE
                        topRatedFilmList.visibility=View.GONE
                        topRatedFilmError.visibility=View.GONE
                    }else{
                        topRatedFilmProgressBar.visibility=View.GONE
                    }


                }

            })


        }







}