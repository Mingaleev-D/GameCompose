package com.example.gamecompose.ui.screens.base

sealed class Screen(val route:String){
   object HomeScreen:Screen(route = "")
}
