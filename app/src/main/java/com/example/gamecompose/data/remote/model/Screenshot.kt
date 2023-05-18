package com.example.gamecompose.data.remote.model


import com.google.gson.annotations.SerializedName

data class Screenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
){
    fun toScreenshot(): com.example.gamecompose.domain.model.Screenshot1 {
        return com.example.gamecompose.domain.model.Screenshot1(
            id,
            image
        )
    }
}