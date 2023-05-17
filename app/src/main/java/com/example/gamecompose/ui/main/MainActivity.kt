package com.example.gamecompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gamecompose.ui.theme.GameComposeTheme
import com.example.gamecompose.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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
         GameComposeTheme(
             darkTheme = isSystemInDarkTheme()
         ){
            HomePage()
         }
      }
   }

   @Composable
   fun HomePage() {
      Text(text = "Home page",
           color = MaterialTheme.colorScheme.onSurface)
   }
}
