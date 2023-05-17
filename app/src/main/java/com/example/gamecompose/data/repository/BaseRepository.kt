package com.example.gamecompose.data.repository

import com.example.gamecompose.data.common.ResourceNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author : Mingaleev D
 * @data : 17.05.2023
 */

class BaseRepository {

   suspend fun <T> invokeApi(apiCall: suspend () -> T): ResourceNetwork<T> {
      return withContext(Dispatchers.IO) {
         try {
            ResourceNetwork.Success(apiCall.invoke())
         } catch (throwable: Throwable) {
            ResourceNetwork.Error(error = throwable)
         }
      }
   }
}