package com.example.gamecompose.domain.repository

import com.example.gamecompose.data.common.ResourceNetwork
import com.example.gamecompose.data.remote.model.GameDetailsItem
import com.example.gamecompose.domain.model.Game
import com.example.gamecompose.domain.model.GameDetail

/**
 * @author : Mingaleev D
 * @data : 17.05.2023
 */

interface GameRepository {

   suspend fun getAllGames(): ResourceNetwork<List<Game>>

   suspend fun getGame(id:Int):ResourceNetwork<GameDetail?>
}