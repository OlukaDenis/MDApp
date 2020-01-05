package com.systec.mdapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.systec.mdapp.data.repository.MovieRepository;
import com.systec.mdapp.data.repository.SlidesRepository;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.model.Slide;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository slidesRepository;
    private LiveData<List<Movie>> movies;
    private static final String TAG = "MovieViewModel";

    public MovieViewModel(Application application){
        super(application);
        slidesRepository = new MovieRepository(application);
        movies = slidesRepository.getPopularMovies();
    }

    public LiveData<List<Movie>> getAllPopularMovies(){
        return movies;
    }
}
