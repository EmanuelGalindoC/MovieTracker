package com.example.movietracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movietracker.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
