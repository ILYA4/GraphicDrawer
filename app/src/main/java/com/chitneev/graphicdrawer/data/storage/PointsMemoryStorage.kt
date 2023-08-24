package com.chitneev.graphicdrawer.data.storage

import com.chitneev.graphicdrawer.domain.models.Point

interface PointsMemoryStorage {
    fun savePoints(points: List<Point>)
    fun getPoints(): List<Point>?
}