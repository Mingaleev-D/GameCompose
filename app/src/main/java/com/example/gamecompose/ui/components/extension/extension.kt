package com.example.gamecompose.ui.components.extension

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamecompose.domain.model.Game

/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
   item(
       span = { GridItemSpan(maxLineSpan) },
       content = content
   )
}

fun List<Game>.getUrls(): List<String>{
   return takeRandomElements(numberOfElements = 3).map { it.thumbnail }
}

fun <T> List<T>.takeRandomElements(numberOfElements: Int): List<T>{
   return if(numberOfElements > size) this
   else asSequence().shuffled().take(numberOfElements).toList()
}