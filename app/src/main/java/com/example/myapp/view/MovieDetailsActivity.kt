package com.example.myapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.myapp.Constants
import com.example.myapp.databinding.ActivityMovieDetailsBinding
import com.example.myapp.data.MovieDetails
import com.example.myapp.model.api.ApiInterface
import com.example.myapp.viewModel.MoviesViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel = MoviesViewModel()
    private lateinit var binding: ActivityMovieDetailsBinding

    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var scope: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)
        Log.d("MyLog", "Id: $id")

        initViews()
        initObservers()
        viewModel.getMovieDetails(id)
    }

    private fun initObservers() {
       viewModel.apply {
           movieDetails.observe(this@MovieDetailsActivity) {
               setMovieInformation(it)
           }
       }
    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        title.text = movieDetails?.title
        releaseDate.text = movieDetails?.release_date.toString()
        scope.text = movieDetails?.vote_average.toString()
        overview.text = movieDetails?.overview

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + movieDetails?.backdrop_path)
            .into(banner)
        Log.d("MyLog", "Image: ${movieDetails?.backdrop_path}")
    }

    private fun initViews() {
        binding.apply {
            title = moviesDetailsTitle
            releaseDate = moviesDetailsData
            scope = moviesDetailsScore
            overview = moviesDetailsBodyOverview
            banner = moviesDetailsImageBanner
        }
    }
}