package com.chitneev.graphicdrawer.domain.repository

import com.chitneev.graphicdrawer.data.service.PointsService
import com.chitneev.graphicdrawer.domain.models.Point
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PointsRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hr-challenge.interactivestandard.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<PointsService>()


    suspend fun getPoints(count: Int) : List<Point> {
        return try {
            service.getPoints(count).points.map {
                Point(x = it.x, y = it.y)
            }
        } catch (e: Exception) {
            throw e
        }
    }
}