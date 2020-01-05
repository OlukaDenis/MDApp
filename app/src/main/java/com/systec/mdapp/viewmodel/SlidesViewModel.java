package com.systec.mdapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.systec.mdapp.data.repository.SlidesRepository;
import com.systec.mdapp.model.Slide;

import java.util.List;
import java.util.Objects;

public class SlidesViewModel extends AndroidViewModel {
    private SlidesRepository slidesRepository;
    private LiveData<List<Slide>> slides;
    private static final String TAG = "SlidesViewModel";

    public SlidesViewModel(Application application){
        super(application);
        slidesRepository = new SlidesRepository(application);
        slides = slidesRepository.getTodayTrendingMovies();
    }

    public LiveData<List<Slide>> getAllSlides(){
        return slides;
    }
}
