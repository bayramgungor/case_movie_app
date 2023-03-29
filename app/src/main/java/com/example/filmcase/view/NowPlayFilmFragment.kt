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
import com.example.filmcase.viewmodel.NowPlayViewModel
import com.example.filmcase.viewmodel.PopulerFilmViewModel
import com.example.filmcase.viewmodel.TopRatedFilmViewModel
import kotlinx.android.synthetic.main.now_play_film_fragment.*
import kotlinx.android.synthetic.main.populer_film_fragmnet.*
import kotlinx.android.synthetic.main.upcoming_film_fragment.*

class NowPlayFilmFragment:Fragment() {
    private lateinit var viewModel: NowPlayViewModel
    private val filmAdapter= FilmAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.now_play_film_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(NowPlayViewModel::class.java)
        viewModel.refreshData()
        nowPlayFilmList.layoutManager= LinearLayoutManager(context)
        nowPlayFilmList.adapter=filmAdapter

        nowPlaySwipeRefreshLayout.setOnRefreshListener {
            nowPlayFilmList.visibility=View.GONE
            nowPlayFilmError.visibility=View.GONE
            nowPlayFilmProgressBar.visibility=View.GONE
            viewModel.refreshData()
            nowPlaySwipeRefreshLayout.isRefreshing=false

        }

        observeLiveData()

    }
    fun observeLiveData(){
        viewModel.nowPlayFilms.observe(viewLifecycleOwner, Observer { nowPlayFilm->

            nowPlayFilm.let {
                nowPlayFilmList.visibility=View.VISIBLE
                filmAdapter.updateFilmList(nowPlayFilm.results)
            }
        })
        viewModel.nowPlayFilsError.observe(viewLifecycleOwner, Observer { error->

            error.let {
                if(it){
                    nowPlayFilmError.visibility=View.VISIBLE

                }else{
                    nowPlayFilmError.visibility=View.GONE
                }
            }
        })
        viewModel.nowPlayFilmsLoading.observe(viewLifecycleOwner, Observer { loading->

            loading.let {
                if (it){
                    nowPlayFilmProgressBar.visibility=View.VISIBLE
                    nowPlayFilmList.visibility=View.GONE
                    nowPlayFilmError.visibility=View.GONE
                }else{
                    nowPlayFilmProgressBar.visibility=View.GONE
                }
            }
        })
    }
}