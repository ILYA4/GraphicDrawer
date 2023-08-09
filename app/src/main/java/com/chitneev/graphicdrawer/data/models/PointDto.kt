package com.chitneev.graphicdrawer.data.models

import com.google.gson.annotations.SerializedName

data class PointDto(
    @SerializedName("x") val x: Double,
    @SerializedName("y") val y: Double,
)