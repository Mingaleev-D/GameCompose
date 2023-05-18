package com.example.gamecompose.domain.model

data class Game(
    val developer: String,
    val freetogameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)
