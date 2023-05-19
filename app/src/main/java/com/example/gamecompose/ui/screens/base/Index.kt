package com.example.gamecompose.ui.screens.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamecompose.data.common.Constants.ALL_GAMES_KEY
import com.example.gamecompose.data.common.Constants.SEARCH_SCREEN_FILTER_KEY
import com.example.gamecompose.data.common.ResourceNetwork
import com.example.gamecompose.domain.model.Game
import com.example.gamecompose.ui.components.drawer.NavigationDrawer
import com.example.gamecompose.ui.components.drawer.NavigationDrawerItem
import com.example.gamecompose.ui.screens.game.GameDetailScreen
import com.example.gamecompose.ui.screens.home.HomeScreen
import com.example.gamecompose.ui.screens.search.SearchScreen
import com.example.gamecompose.ui.viewmodel.GameDetailViewModel
import com.example.gamecompose.ui.viewmodel.MainViewModel
import com.example.gamecompose.ui.viewmodel.SearchViewModel
import com.intuit.sdp.R

/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */

@Composable
fun Index(scaffoldState: ScaffoldState,
          navController: NavHostController,
          availableGames: ResourceNetwork<List<Game>>,
          onOpenDrawer: () -> Unit,
          onSearchButtonClick: () -> Unit,
          onGameClick: (Int) -> Unit,
          onPlayTheGameClicked: (String) -> Unit,
          onHomeMenuClick: () -> Unit,
          onPCGamesClick: () -> Unit,
          onWebGamesClick: () -> Unit,
          onLatestGamesClick: () -> Unit
) {
   Scaffold(scaffoldState = scaffoldState,
            drawerShape = RectangleShape,
            drawerContent = {
               NavigationDrawer(
                   header = {
                      Box(modifier = Modifier
                         .size(size = dimensionResource(id = R.dimen._250sdp)),
                          contentAlignment = Alignment.Center) {
                         Image(modifier = Modifier.size(150.dp),
                               painter = painterResource(id = com.example.gamecompose.R.drawable.ic_free_to_play_launcher),
                               contentDescription = "",
                               contentScale = ContentScale.FillHeight)
                      }
                   },
                   content = {
                      NavigationDrawerItem(modifier = Modifier
                         .requiredHeight(45.dp)
                         .padding(5.dp),
                                           iconPainter = painterResource(id = com.example.gamecompose.R.drawable.ic_bars_staggered_solid),
                                           iconColor = MaterialTheme.colors.primary,
                                           text = stringResource(id = com.example.gamecompose.R.string.lbl_home),
                                           textStyle = MaterialTheme.typography.subtitle1,
                                           textColor = MaterialTheme.colors.onBackground,
                                           onClick = {
                                              onHomeMenuClick()
                                           })
                      Spacer(modifier = Modifier.padding(8.dp))
                      NavigationDrawerItem(modifier = Modifier
                         .requiredHeight(45.dp)
                         .padding(5.dp),
                                           iconPainter = painterResource(id = com.example.gamecompose.R.drawable.ic_windows_brands),
                                           iconColor = MaterialTheme.colors.primary,
                                           text = stringResource(id = com.example.gamecompose.R.string.lbl_pc_games),
                                           textStyle = MaterialTheme.typography.subtitle1,
                                           textColor = MaterialTheme.colors.onBackground,
                                           onClick = {
                                              onPCGamesClick()
                                           })
                      Spacer(modifier = Modifier.padding(8.dp))
                      NavigationDrawerItem(modifier = Modifier
                         .requiredHeight(45.dp)
                         .padding(5.dp),
                                           iconPainter = painterResource(id = com.example.gamecompose.R.drawable.ic_window_maximize_solid),
                                           iconColor = MaterialTheme.colors.primary,
                                           text = stringResource(id = com.example.gamecompose.R.string.lbl_web_games),
                                           textStyle = MaterialTheme.typography.subtitle1,
                                           textColor = MaterialTheme.colors.onBackground,
                                           onClick = {
                                              onWebGamesClick()
                                           })
                      Spacer(modifier = Modifier.padding(8.dp))
                      NavigationDrawerItem(modifier = Modifier
                         .requiredHeight(45.dp)
                         .padding(5.dp),
                                           iconPainter = painterResource(id = com.example.gamecompose.R.drawable.ic_arrow_trend_up_solid),
                                           iconColor = MaterialTheme.colors.primary,
                                           text = stringResource(id = com.example.gamecompose.R.string.lbl_latest_games),
                                           textStyle = MaterialTheme.typography.subtitle1,
                                           textColor = MaterialTheme.colors.onBackground,
                                           onClick = {
                                              onLatestGamesClick()
                                           })
                   }
               )

            }) { innerPadding ->
      NavHost(modifier = Modifier.padding(innerPadding),
              navController = navController,
              startDestination = Screen.HomeScreen.route) {
         composable(route = Screen.HomeScreen.route) {
            HomeScreen(onOpenDrawer = { onOpenDrawer() },
                       onSearchButtonClick = { onSearchButtonClick() },
                       onGameClick = { onGameClick(it) },
                       availableGames = availableGames)
         }
         composable(route = Screen.GameDetailScreen.route) {
            val viewModel = hiltViewModel<GameDetailViewModel>()
            GameDetailScreen(viewModel = viewModel,
                             navController = navController,
                             onPlayTheGameClicked = { gameUrl ->
                                onPlayTheGameClicked(gameUrl)
                             })
         }
         composable(route = Screen.SearchScreen.route,
                    arguments = listOf(
                        navArgument(name = SEARCH_SCREEN_FILTER_KEY) {
                           defaultValue = ""
                           type = NavType.StringType
                        }
                    )
         ) {
            val viewModel = hiltViewModel<SearchViewModel>()
            val games =
                navController.previousBackStackEntry?.savedStateHandle?.get<List<Game>>(key = ALL_GAMES_KEY)
                ?: emptyList()
            SearchScreen(viewModel = viewModel,
                         navController = navController,
                         scaffoldState = scaffoldState,
                         games = games
            )
         }
      }
   }
}