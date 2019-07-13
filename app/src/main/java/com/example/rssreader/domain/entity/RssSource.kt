package com.example.rssreader.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RssSource(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val url: String = ""
)