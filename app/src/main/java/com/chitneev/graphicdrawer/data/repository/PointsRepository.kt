package com.chitneev.graphicdrawer.data.repository

import com.chitneev.graphicdrawer.data.models.PointDto

interface PointsRepository {
    suspend fun getPoints(count: Int) : List<PointDto>
}