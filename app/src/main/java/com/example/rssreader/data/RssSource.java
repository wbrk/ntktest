package com.example.rssreader.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RssSource {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name = "";

    @NonNull
    public String url = "";

    public RssSource() {}

    @Ignore
    public RssSource(@NonNull String name, @NonNull String url) {
        this.name = name;
        this.url = url;
    }
}
