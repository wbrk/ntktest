package com.example.rssreader.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RssSource(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String
)