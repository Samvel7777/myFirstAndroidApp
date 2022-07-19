package com.example.myapp.data

data class Movies(

    val page: Int,
    val results: List<Result>,
    val total_page: Int,
    val total_results: Int

)
