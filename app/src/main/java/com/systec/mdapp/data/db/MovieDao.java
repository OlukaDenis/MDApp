package com.systec.mdapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.systec.mdapp.model.Movie;
import com.systec.mdapp.model.Slide;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<Movie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateMovie(Movie movie);

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movies WHERE title==:title")
    Slide getMovie(String title);
}
