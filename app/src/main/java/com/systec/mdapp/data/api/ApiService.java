package com.systec.mdapp.data.api;

import com.systec.mdapp.model.MovieResponse;
import com.systec.mdapp.model.SlideResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("trending/movie/week")
    Call<SlideResponse> getTodayTrendingMovies(@Query("api_key") String apiKey,
                                          @Query("page") int page);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey,
                                         @Query("page") int page);
}
