package com.chitneev.graphicdrawer.data.repository

import com.chitneev.graphicdrawer.domain.models.Point

interface PointsRepository {
    suspend fun fetchPoints(count: Int) : List<Point>
    fun getCachedPoints(): List<Point>
}