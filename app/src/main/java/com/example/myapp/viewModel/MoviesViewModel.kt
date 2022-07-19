package com.example.myapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapp.data.MovieDetails
import com.example.myapp.data.Movies
import com.example.myapp.data.Result
import com.example.myapp.model.repository.MoviesDBRepository
import com.example.myapp.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val moviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = moviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                Log.d("MyLog", "OnResponse Success: $call")
                _movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("MyLog", "OnFailure: ${t.message}")
            }
        })
    }

    fun getMovieDetails(id: Int) {
        val response = moviesRepository.getMovieDetails(id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("MyLog", "OnResponse Success: $call")
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("MyLog", "OnFailure: ${t.message}")
            }

        })
    }
}