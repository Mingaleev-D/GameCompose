package com.example.gamecompose.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamecompose.data.common.Constants.GAME_ID_KEY
import com.example.gamecompose.data.common.ResourceNetwork
import com.example.gamecompose.data.repository.GameRepositoryImpl
import com.example.gamecompose.domain.model.GameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */

@HiltViewModel
class GameDetailViewModel @Inject constructor(private val repositoryImpl: GameRepositoryImpl,
                                              private val savedStateHandle: SavedStateHandle
) : ViewModel() {

   private val _gameDetailState: MutableStateFlow<ResourceNetwork<GameDetail?>> =
       MutableStateFlow(value = ResourceNetwork.Loading())
   val gameDetailState: StateFlow<ResourceNetwork<GameDetail?>>
      get() = _gameDetailState

   private val _gameTitleState = mutableStateOf(value = "")
   val gameTitle: State<String>
      get() = _gameTitleState


   init {
      savedStateHandle.get<String>(key = GAME_ID_KEY)?.let { id ->
         getGameDetail(id = id.toInt())
      }
   }


   private fun getGameDetail(id: Int){
      viewModelScope.launch {
         _gameDetailState.value = repositoryImpl.getGame(id = id)
      }
   }

   fun setGameTitle(title: String){
      _gameTitleState.value = title
   }
}
