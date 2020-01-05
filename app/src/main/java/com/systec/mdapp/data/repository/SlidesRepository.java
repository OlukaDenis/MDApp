package com.systec.mdapp.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.systec.mdapp.data.api.ApiClient;
import com.systec.mdapp.data.api.ApiService;
import com.systec.mdapp.data.db.AppDatabase;
import com.systec.mdapp.data.db.SlidesDao;
import com.systec.mdapp.model.Slide;
import com.systec.mdapp.model.SlideResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.systec.mdapp.utils.Constants.API_KEY;

public class SlidesRepository {
    private ApiService apiService;
    private AppDatabase database;
    private SlidesDao slidesDao;
    private static final String TAG = "SlidesRepository";

    public SlidesRepository(Application application){
        database = AppDatabase.getDatabase(application);
        slidesDao = database.slidesDao();
    }

    public LiveData<List<Slide>> getTodayTrendingMovies(){
        refreshSlides();
        return slidesDao.getAllSlides(); //getting the saved movies
    }

    private void refreshSlides() {
        apiService = ApiClient.getApiService();
        apiService.getTodayTrendingMovies(API_KEY,1).enqueue(new Callback<SlideResponse>() {
            @Override
            public void onResponse(Call<SlideResponse> call, Response<SlideResponse> response) {
                if (response.isSuccessful()){
                    SlideResponse slidesResponse = response.body();
                    assert slidesResponse != null;
                    List<Slide> movies = slidesResponse.getResults();
                    Log.d(TAG, "onResponse: "+movies.size());
                    slidesDao.insertSlides(movies);
                }

            }

            @Override
            public void onFailure(Call<SlideResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
