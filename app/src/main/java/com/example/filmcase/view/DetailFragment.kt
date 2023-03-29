package com.example.filmcase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.filmcase.R
import com.example.filmcase.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private var filmUuid =0
    private lateinit var viewModel :DetailViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)






        }

        fun observeLiveData(){
            viewModel.detailLiveData.observe(viewLifecycleOwner, Observer { detail->
                detail.let {
                    detailBaslik.text=detail.filmName
                    detailKisaAciklama.text=detail.filmShortDescription

                }
            })
        }
    }


