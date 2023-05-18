package com.example.gamecompose.data.remote.model


import com.example.gamecompose.domain.model.Game
import com.google.gson.annotations.SerializedName

data class GameDtoItem(
    @SerializedName("developer")
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
) {
   fun toGame(): Game {
      return Game(developer,
                  freetogameProfileUrl,
                  gameUrl,
                  genre,
                  id,
                  platform,
                  publisher,
                  releaseDate,
                  shortDescription,
                  thumbnail,
                  title)


   }
}