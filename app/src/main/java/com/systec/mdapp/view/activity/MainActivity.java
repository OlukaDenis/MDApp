package com.systec.mdapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.systec.mdapp.R;
import com.systec.mdapp.data.api.ApiService;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.view.adapter.Movie_adapter;
import com.systec.mdapp.view.adapter.SlidePagerAdapter;
import com.systec.mdapp.model.Slide;
import com.systec.mdapp.viewmodel.MovieViewModel;
import com.systec.mdapp.viewmodel.SlidesViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView movieRv;
    private SlidesViewModel slidesViewModel;
    private MovieViewModel movieViewModel;
    private ApiService apiService;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        movieRv = findViewById(R.id.recyclerView_movie);

        slidesViewModel = ViewModelProviders.of(this).get(SlidesViewModel.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        //Slider Timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.slideTimer(),400, 6000);
        indicator.setupWithViewPager(sliderPager,true);

        //RecyclerView
        movieRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

        getMovies();
        populateSlider();


    }

    private void populateSlider() {
        slidesViewModel.getAllSlides().observe(this, new Observer<List<Slide>>() {
            @Override
            public void onChanged(List<Slide> slides) {
                sliderPager.setAdapter(new SlidePagerAdapter(getApplicationContext(), slides));
            }
        });

    }

    private void getMovies() {
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieRv.setAdapter(new Movie_adapter(getApplicationContext(), movies));
            }
        });

    }

    class slideTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()< 5){
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
