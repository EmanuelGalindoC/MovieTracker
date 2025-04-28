package com.example.movietracker.di

import android.content.Context
import androidx.room.Room
import com.example.movietracker.data.local.MovieDatabase
import com.example.movietracker.data.remote.MovieApiService
import com.example.movietracker.data.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieApiService(): MovieApiService = RetrofitInstance.api

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db").build()

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()
}