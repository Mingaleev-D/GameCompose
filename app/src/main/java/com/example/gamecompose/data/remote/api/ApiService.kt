package com.example.gamecompose.data.remote.api

import com.example.gamecompose.data.common.Constants.END_POINT
import com.example.gamecompose.data.common.Constants.END_POINT_GAME
import com.example.gamecompose.data.common.Constants.END_POINT_PLATFORM
import com.example.gamecompose.data.common.Constants.END_POINT_SORT_BY
import com.example.gamecompose.data.common.Constants.GAME_ID_KEY
import com.example.gamecompose.data.remote.model.GameDetailsItem
import com.example.gamecompose.data.remote.model.GameDtoItem
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 17.05.2023
 */

interface ApiService {

   @GET(END_POINT)
   suspend fun getAllGames(): List<GameDtoItem>

   @GET(END_POINT_GAME)
   suspend fun getGame(
       @Query(GAME_ID_KEY) id: Int
   ): GameDetailsItem?

   @GET(END_POINT)
   suspend fun getGameByPlatform(
       @Query(END_POINT_PLATFORM) platform: String
   ): List<GameDtoItem>

   @GET(END_POINT_GAME)
   suspend fun sortGame(
       @Query(END_POINT_SORT_BY) criteria: String
   ): List<GameDtoItem>
}