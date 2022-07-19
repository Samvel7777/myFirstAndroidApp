package com.example.myapp.model.repository

import com.example.myapp.data.MovieDetails
import com.example.myapp.data.Movies
import retrofit2.Call

/**
 * Repository provides information taken from MovieDB API
 */
interface MoviesDBRepository {

    /**
     * Returns list of popular [Movies]
     */
    fun getMovies(): Call<Movies>

    /**
     * Returns information for a single movie by returning [MovieDetails]
     * @param id - identification number of the needed movie
     */
    fun getMovieDetails(id: Int): Call<MovieDetails>
}