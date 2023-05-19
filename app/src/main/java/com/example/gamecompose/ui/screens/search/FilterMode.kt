package com.example.gamecompose.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamecompose.domain.model.Game
import com.example.gamecompose.ui.components.CarouselView
import com.example.gamecompose.ui.components.SearchDetailCard
import com.example.gamecompose.ui.components.TopBar
import com.example.gamecompose.ui.components.extension.getUrls

/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */

@Composable
fun FilterMode(
    games: List<Game>,
    isLoading: Boolean,
    onGameClick: (Int) -> Unit,
    onOpenDrawer: () -> Unit,
    onSearchClick: () -> Unit
) {
   LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.spacedBy(space = 15.dp),
              modifier = Modifier.fillMaxSize()
   ) {

      item {
         TopBar(onOpenDrawer = onOpenDrawer,
                onSearchButtonClick = onSearchClick
         )
      }

      item {
         CarouselView(modifier = Modifier
            .requiredHeight(height = 260.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
                      urls = games.getUrls(),
                      shape = MaterialTheme.shapes.medium,
                      crossFade = 1000
         )
         Spacer(modifier = Modifier.height(30.dp))
      }

      if (isLoading) {
         item {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
               CircularProgressIndicator()
            }
         }
      } else {
         items(items = games) { game ->
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
               SearchDetailCard(
                   game = game,
                   onClick = onGameClick
               )
            }
         }
      }
   }

}