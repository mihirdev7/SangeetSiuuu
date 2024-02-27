package com.example.sangeet.Models

import com.example.sangeet.Models.Data

data class MyData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)