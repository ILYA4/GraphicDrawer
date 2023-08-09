package com.chitneev.graphicdrawer.data.service

import com.chitneev.graphicdrawer.data.models.PointsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PointsService {

    @GET("api/test/points")
    suspend fun getPoints(@Query("count") count: Int) : PointsResponse

}