package com.chitneev.graphicdrawer.data.models

import com.google.gson.annotations.SerializedName

data class PointsResponse(
    @SerializedName("points") val points: List<PointDto>,
)