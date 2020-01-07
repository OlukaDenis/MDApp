package com.systec.mdapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.systec.mdapp.model.Slide;

import java.util.List;

@Dao
public interface SlidesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertSlides(List<Slide> slides);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSlide(Slide slide);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateSlide(Slide slide);

    @Query("SELECT * FROM movie_slides")
    LiveData<List<Slide>> getAllSlides();

    @Query("select * from movie_slides where title==:title")
    Slide getSlide(String title);
}
