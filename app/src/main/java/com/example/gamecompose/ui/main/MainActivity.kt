package com.example.gamecompose.ui.main

import GameComposeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.gamecompose.ui.screens.base.Index
import com.example.gamecompose.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   private val mainViewModel: MainViewModel by viewModels()
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      installSplashScreen().apply {
         setKeepOnScreenCondition {
            mainViewModel.splashScreenVisible.value
         }
      }
      setContent {
         val scaffoldState = rememberScaffoldState()
         val navController = rememberNavController()
         val availableGames by mainViewModel.games.collectAsState()
         val scope = rememberCoroutineScope()
         val uriHandler = LocalUriHandler.current

         GameComposeTheme(
             darkTheme = isSystemInDarkTheme()
         ) {
            Index(
                scaffoldState = scaffoldState,
                navController = navController,
                availableGames = availableGames,
                onOpenDrawer = {
                   scope.launch { scaffoldState.drawerState.open() }
                },
                onSearchButtonClick = {

                },
                onGameClick = { gameId ->
                   navController.navigate(route = "gameDetails/$gameId")
                },
                onPlayTheGameClicked = {gameUrl ->
                   uriHandler.openUri(uri = gameUrl)
                }
            )
         }
      }
   }
}
