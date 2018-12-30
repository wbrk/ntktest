package com.example.rssreader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.rssreader.App;

@Database(entities = {RssSource.class}, version = 1)
public abstract class RssDatabase extends RoomDatabase {
    private static RssDatabase instance;

    public abstract RssSourceDao sourceDao();

    @NonNull
    public static RssDatabase instance() {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(App.getAppContext(), RssDatabase.class, "db")
                    .allowMainThreadQueries() // todo remove me
                    .build();
        }
        return instance;
    }
}
