package com.example.myapp.model.repository

import com.example.myapp.Constants
import com.example.myapp.data.MovieDetails
import com.example.myapp.data.Movies
import com.example.myapp.model.api.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "en-US", 1)
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, Constants.API_KEY)
    }

}