package com.example.gamecompose.domain.model

import com.example.gamecompose.data.remote.model.MinimumSystemRequirements
import com.example.gamecompose.data.remote.model.Screenshot

data class GameDetail(
    val description: String,
    val developer: String,
    val freeToGameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirements?,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenShots: List<Screenshot>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)
