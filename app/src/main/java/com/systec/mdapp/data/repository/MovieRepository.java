package com.systec.mdapp.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.systec.mdapp.data.api.ApiClient;
import com.systec.mdapp.data.api.ApiService;
import com.systec.mdapp.data.db.AppDatabase;
import com.systec.mdapp.data.db.MovieDao;
import com.systec.mdapp.data.db.SlidesDao;
import com.systec.mdapp.model.Movie;
import com.systec.mdapp.model.MovieResponse;
import com.systec.mdapp.model.Slide;
import com.systec.mdapp.model.SlideResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.systec.mdapp.utils.Constants.API_KEY;

public class MovieRepository {
    private ApiService apiService;
    private AppDatabase database;
    private MovieDao movieDao;
    private static final String TAG = "MovieRepository";

    public MovieRepository(Application application){
        database = AppDatabase.getDatabase(application);
        movieDao = database.movieDao();
    }

    public LiveData<List<Movie>> getPopularMovies(){
        refreshSlides();
        return movieDao.getAllMovies(); //getting the saved movies
    }

    private void refreshSlides() {
        apiService = ApiClient.getApiService();
        apiService.getPopularMovies(API_KEY,1).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
                        MovieResponse movieResponse = response.body();
                        List<Movie> movies = movieResponse.getResults();
                        Log.d(TAG, "onResponse: "+movies.size());
                        movieDao.insertMovies(movies);
                    }

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
