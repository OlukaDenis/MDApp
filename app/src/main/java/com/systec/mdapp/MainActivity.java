package com.systec.mdapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.systec.mdapp.adapter.Movie_adapter;
import com.systec.mdapp.adapter.SlidePagerAdapter;
import com.systec.mdapp.model.movie;
import com.systec.mdapp.model.slide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<slide> mSlideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView movieRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        movieRv = findViewById(R.id.recyclerView_movie);

        //Dummy Data for slider
        mSlideList = new ArrayList<>();
        mSlideList.add(new slide(R.drawable.images,"Title \n plot explanation here"));
        mSlideList.add(new slide(R.drawable.slide2,"Title \n plot explanation here"));
        mSlideList.add(new slide(R.drawable.slide3,"Title \n plot explanation here"));
        mSlideList.add(new slide(R.drawable.slide4,"Title \n plot explanation here"));
        mSlideList.add(new slide(R.drawable.slide5,"Title \n plot explanation here"));
        SlidePagerAdapter adapter = new SlidePagerAdapter(this, mSlideList);
        sliderPager.setAdapter(adapter);

        //Slider Timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.slideTimer(),400, 6000);
        indicator.setupWithViewPager(sliderPager,true);

        //RecyclerView
        //dummy Dtata
        List<movie> movieList = new ArrayList<>();
        movieList.add(new movie("AquaMan",R.drawable.thumb2));
        movieList.add(new movie("Captain Marvel",R.drawable.thumb3));
        movieList.add(new movie("How to train your Dragon HomeComing",R.drawable.thumb4));
        movieList.add(new movie("Venom",R.drawable.thumb5));

        Movie_adapter movie_adapter = new Movie_adapter(this, movieList);
        movieRv.setAdapter(movie_adapter);
        movieRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));



    }

    class slideTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<mSlideList.size()-1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
