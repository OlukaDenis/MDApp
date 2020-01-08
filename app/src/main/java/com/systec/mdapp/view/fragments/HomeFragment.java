package com.systec.mdapp.view.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.systec.mdapp.R;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.model.Slide;
import com.systec.mdapp.view.adapter.Movie_adapter;
import com.systec.mdapp.view.adapter.SlidePagerAdapter;
import com.systec.mdapp.view.interfaces.MovieitemClickListener;
import com.systec.mdapp.viewmodel.HomeViewModel;
import com.systec.mdapp.viewmodel.MovieViewModel;
import com.systec.mdapp.viewmodel.SlidesViewModel;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView movieRv;
    private SlidesViewModel slidesViewModel;
    private MovieViewModel movieViewModel;
    private static final String TAG = "HomeFragment";
    private View mView;
    private Context mContext;
    private MovieitemClickListener mListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initialiseViews();
        initTimer();
        initRecyclerView();
        return mView;
    }

    private void initRecyclerView() {
        //RecyclerView
        movieRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL , false));

        getMovies();
        populateSlider();
    }

    private void initTimer() {
        //Slider Timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.slideTimer(),400, 6000);
        indicator.setupWithViewPager(sliderPager,true);
    }

    private void initialiseViews() {
        sliderPager = mView.findViewById(R.id.slider_pager);
        indicator = mView.findViewById(R.id.indicator);
        movieRv = mView.findViewById(R.id.recyclerView_movie);
        if (getContext()!=null){
            mContext = getContext();
        }
        else mContext = getActivity();

        slidesViewModel = ViewModelProviders.of(this).get(SlidesViewModel.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    private void populateSlider() {
        slidesViewModel.getAllSlides().observe(this, new Observer<List<Slide>>() {
            @Override
            public void onChanged(List<Slide> slides) {
                sliderPager.setAdapter(new SlidePagerAdapter(mContext, slides));
            }
        });

    }

    private void getMovies() {
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieRv.setAdapter(new Movie_adapter(mContext, movies, mListener));
            }
        });

    }

    class slideTimer extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
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
