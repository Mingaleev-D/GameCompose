package com.example.gamecompose.data.remote.model


import com.google.gson.annotations.SerializedName

data class MinimumSystemRequirements(
    @SerializedName("graphics")
    val graphics: String,
    @SerializedName("memory")
    val memory: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("processor")
    val processor: String,
    @SerializedName("storage")
    val storage: String
){
    fun toMinimumSystemRequirements(): com.example.gamecompose.domain.model.MinimumSystemRequirements1 {
        return com.example.gamecompose.domain.model.MinimumSystemRequirements1(
            graphics,
            memory,
            os,
            processor,
            storage
        )
    }
}