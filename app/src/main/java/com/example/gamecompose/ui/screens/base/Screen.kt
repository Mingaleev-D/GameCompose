package com.example.gamecompose.ui.screens.base

sealed class Screen(val route:String){
   object HomeScreen:Screen(route = "home")
   object GameDetailScreen: Screen(route = "gameDetails/{id}")
}
