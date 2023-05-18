package com.example.gamecompose.ui.components

import androidx.compose.runtime.Composable
import com.example.gamecompose.data.common.ResourceNetwork

/**
 * @author : Mingaleev D
 * @data : 18.05.2023
 */

@Composable
fun <T> StateHandler(
    state: ResourceNetwork<T>,
    onLoading: @Composable (ResourceNetwork<T>) -> Unit,
    onFailure: @Composable (ResourceNetwork<T>) -> Unit,
    onSuccess: @Composable (ResourceNetwork<T>) -> Unit
){

   if(state is ResourceNetwork.Loading){
      onLoading(state)
   }
   if(state is ResourceNetwork.Error){
      onFailure(state)
   }
   onSuccess(state)

}