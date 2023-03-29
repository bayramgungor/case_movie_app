package com.example.filmcase.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.filmcase.R
import com.example.filmcase.adater.ViewPagerAdapter
import com.example.filmcase.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

var tabTitle = arrayOf("Populer","Top Rated","UpComing","Play Now")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pager =findViewById<ViewPager2>(R.id.viewPager2)
        var tabLayout=findViewById<TabLayout>(R.id.tabs)

        val actionBar=supportActionBar
        actionBar?.title=("Movies")




        pager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)




        TabLayoutMediator(tabLayout,pager){
            tab, position->
            tab.text=tabTitle[position]
        }.attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.film_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.popular){
            val intent=Intent(this,PopulerFilmFragment::class.java)
            startActivity(intent)
        }
        else if (item.itemId==R.id.now_play){
            val intent=Intent(this,NowPlayFilmFragment::class.java)
            startActivity(intent)
        }
        else if (item.itemId==R.id.top_rated){
            val intent=Intent(this,NowPlayFilmFragment::class.java)
            startActivity(intent)
        }else if (item.itemId==R.id.upcoming){
            val intent=Intent(this,UpComingFilmFragment::class.java)
            startActivity(intent)

        }


        return super.onOptionsItemSelected(item)
    }

}