package com.example.gamecompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gamecompose.R
import com.example.gamecompose.domain.model.Game
import com.example.gamecompose.ui.theme.RedErrorLight


/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */


@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
   Card(shape = MaterialTheme.shapes.small,
        modifier = modifier
           .padding(all = 1.dp)
           .clickable { onClick() },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface
   ) {
      Column(modifier = Modifier.fillMaxSize()
      ) {
         NetworkImage(url = game.thumbnail,
                      contentScale = ContentScale.Crop,
                      crossFade = 1000,
                      modifier = Modifier.fillMaxHeight(fraction = 0.5f),
                      onLoading = {
                         ConstraintLayout(modifier = Modifier.fillMaxSize(fraction = 0.5f)) {
                            val indicatorRef = createRef()
                            CircularProgressIndicator(modifier = Modifier.constrainAs(indicatorRef) {
                               top.linkTo(parent.top)
                               bottom.linkTo(parent.bottom)
                               start.linkTo(parent.start)
                               end.linkTo(parent.end)
                            }
                            )
                         }
                      },
                      onError = {
                         Icon(painter = painterResource(id = R.drawable.ic_warning),
                              contentDescription = "",
                              tint = RedErrorLight
                         )
                      }
         )
         Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 3.dp),
                verticalArrangement = Arrangement.SpaceBetween
         ) {
            Column {
               Text(
                   text = game.title,
                   modifier = Modifier
                      .padding(all = 5.dp)
                      .align(alignment = Alignment.CenterHorizontally),
                   style = MaterialTheme.typography.body1,
                   overflow = TextOverflow.Ellipsis,
                   color = MaterialTheme.colors.onPrimary,
                   textAlign = TextAlign.Center
               )
               Box(modifier = Modifier
                  .fillMaxWidth(fraction = 0.95f)
                  .fillMaxHeight(fraction = 0.6f)) {
                  Text(text = game.shortDescription,
                       modifier = Modifier.fillMaxWidth(fraction = 0.85f),
                       style = MaterialTheme.typography.caption,
                       color = MaterialTheme.colors.onSurface,
                       overflow = TextOverflow.Ellipsis
                  )
               }
            }
            Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                   .fillMaxWidth()
                   .padding(end = 5.dp)
            ) {
               Chip(
                   backgroundColor = MaterialTheme.colors.primaryVariant,
                   borderWidth = 1.dp,
               ) {
                  Text(modifier = Modifier.padding(all = 6.dp),
                       text = game.genre,
                       style = MaterialTheme.typography.caption,
                       color = Color.Black
                  )
               }
               Spacer(modifier = Modifier.padding(end = 3.dp))
               Platform(text = game.platform)
            }
         }
      }
   }

}