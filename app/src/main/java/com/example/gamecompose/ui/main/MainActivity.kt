package com.example.gamecompose.ui.main

import GameComposeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.gamecompose.data.common.Constants.ALL_GAMES_KEY
import com.example.gamecompose.data.common.Constants.BROWSER_GAMES
import com.example.gamecompose.data.common.Constants.FILTER_MODE_KEY
import com.example.gamecompose.data.common.Constants.LATEST_GAMES
import com.example.gamecompose.data.common.Constants.PC_GAMES
import com.example.gamecompose.data.common.Constants.SEARCH_MODE_KEY
import com.example.gamecompose.ui.screens.base.Index
import com.example.gamecompose.ui.screens.base.Screen
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
                   scope.launch {
                      val path = "search?mode=$SEARCH_MODE_KEY"
                      navController.currentBackStackEntry?.savedStateHandle?.set(
                          key = ALL_GAMES_KEY,
                          value = availableGames.data?: emptyList()
                      )
                      navController.navigate(route = path)
                   }
                },
                onGameClick = { gameId ->
                   navController.navigate(route = "gameDetails/$gameId")
                },
                onPlayTheGameClicked = {gameUrl ->
                   uriHandler.openUri(uri = gameUrl)
                },
                onHomeMenuClick = {
                   scope.launch {
                     // val path = Screen.HomeScreen.route
                      scaffoldState.drawerState.close()
                      navController.navigate(route = Screen.HomeScreen.route)
                   }
                },
                onPCGamesClick = {
                   scope.launch {
                      val path = "search?mode=$FILTER_MODE_KEY&filter=$PC_GAMES"
                      scaffoldState.drawerState.close()
                      navController.navigate(route = path)
                   }
                },
                onWebGamesClick = {
                   scope.launch {
                      val path = "search?mode=$FILTER_MODE_KEY&filter=$BROWSER_GAMES"
                      scaffoldState.drawerState.close()
                      navController.navigate(route = path)
                   }
                },
                onLatestGamesClick = {
                   scope.launch {
                      val path = "search?mode=$FILTER_MODE_KEY&filter=$LATEST_GAMES"
                      scaffoldState.drawerState.close()
                      navController.navigate(route = path)
                   }
                }
            )
         }
      }
   }
}
