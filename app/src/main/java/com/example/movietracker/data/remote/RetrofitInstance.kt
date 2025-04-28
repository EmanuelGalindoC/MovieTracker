package com.example.movietracker.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: MovieApiService = retrofit.create(MovieApiService::class.java)
}