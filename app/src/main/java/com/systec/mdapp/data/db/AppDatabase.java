package com.systec.mdapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.systec.mdapp.model.Movie;
import com.systec.mdapp.model.Slide;

@Database(entities = {Slide.class, Movie.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SlidesDao slidesDao();
    public abstract MovieDao movieDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "Movie.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
