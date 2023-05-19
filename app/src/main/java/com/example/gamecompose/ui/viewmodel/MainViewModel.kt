package com.example.gamecompose.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamecompose.data.common.Constants.CURRENT_ROUTE_KEY
import com.example.gamecompose.data.common.ResourceNetwork
import com.example.gamecompose.data.repository.GameRepositoryImpl
import com.example.gamecompose.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 17.05.2023
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val repositoryImpl: GameRepositoryImpl,
                                        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

   private val _splashScreenVisible = mutableStateOf(value = false)
   val splashScreenVisible: State<Boolean>
      get() = _splashScreenVisible

   private val _games: MutableStateFlow<ResourceNetwork<List<Game>>> =
       MutableStateFlow(value = ResourceNetwork.Loading())
   val games: StateFlow<ResourceNetwork<List<Game>>>
      get() = _games

   private val _currentRoute: MutableStateFlow<String> =
       MutableStateFlow(value = savedStateHandle.get(key = CURRENT_ROUTE_KEY) ?: "")
   val currentRoute: StateFlow<String>
      get() = _currentRoute

   init {
      viewModelScope.launch {
         _splashScreenVisible.value = true
         _games.value = repositoryImpl.getAllGames()
         _splashScreenVisible.value = false
      }
   }

   fun setRoute(route: String){
      _currentRoute.value = route
   }
}