package com.example.gamecompose.data.remote.api

import com.example.gamecompose.data.common.Constants.END_POINT
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

   @GET("game")
   suspend fun getGame(
       @Query("id") id: Int
   ): GameDetailsItem?
}