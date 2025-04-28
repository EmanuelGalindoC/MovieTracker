package com.example.movietracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val year: Int,
    val imageUrl: String,
    var isFavorite: Boolean = false,
    var isSeen: Boolean = false
)