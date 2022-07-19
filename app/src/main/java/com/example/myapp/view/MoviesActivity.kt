package com.example.myapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.view.adapter.CustomAdapter
import com.example.myapp.databinding.ActivityMoviesBinding
import com.example.myapp.data.ItemViewModel
import com.example.myapp.data.Movies
import com.example.myapp.model.api.ApiInterface
import com.example.myapp.viewModel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val viewModel: MoviesViewModel = MoviesViewModel()
    private lateinit var moviesRecycler: RecyclerView
    private lateinit var moviesAdapter: CustomAdapter
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MyLog", "MoviesActivity: start")

        initViews()
        initObservers()
        viewModel.getMovies()
    }

    private fun initObservers() {
        viewModel.apply {
            movies.observe(this@MoviesActivity) {
                moviesAdapter = CustomAdapter(it, this@MoviesActivity)
                moviesRecycler.adapter = moviesAdapter
            }
        }
    }

    private fun initViews() {
        moviesRecycler = binding.recyclerview
        moviesRecycler.layoutManager = GridLayoutManager(this, 2)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
        Log.d("MyLog", "MoviesActivity: Application been closed")
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}