package com.example.gamecompose.data.repository

import com.example.gamecompose.data.common.ResourceNetwork
import com.example.gamecompose.data.remote.api.ApiService
import com.example.gamecompose.domain.model.Game
import com.example.gamecompose.domain.repository.GameRepository
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17.05.2023
 */

class GameRepositoryImpl @Inject constructor(private val api: ApiService) : BaseRepository(), GameRepository {

   override suspend fun getAllGames(): ResourceNetwork<List<Game>> {
      val response = invokeApi {
         api.getAllGames()
      }
      return when (response) {
         is ResourceNetwork.Error -> ResourceNetwork.Error(error = response.error)
         is ResourceNetwork.Loading -> ResourceNetwork.Loading()
         is ResourceNetwork.Success -> ResourceNetwork.Success(
             data = response.data?.map { it.toGame() } ?: emptyList()
         )
      }
   }
}