package com.example.filmcase.adater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmcase.view.*

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {

     return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {

            0 -> return PopulerFilmFragment()
            1 -> return TopRatedFilmFragment()
            2 -> return UpComingFilmFragment()
            3 -> return NowPlayFilmFragment()
            else->PopulerFilmFragment()

        }


    }
}