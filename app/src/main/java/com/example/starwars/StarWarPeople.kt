package com.example.starwars

data class StarWarPeople(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)