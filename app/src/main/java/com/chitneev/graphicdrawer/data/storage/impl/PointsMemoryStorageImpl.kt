package com.chitneev.graphicdrawer.data.storage.impl

import com.chitneev.graphicdrawer.data.storage.PointsMemoryStorage
import com.chitneev.graphicdrawer.domain.models.Point
import javax.inject.Inject

class PointsMemoryStorageImpl @Inject constructor() : PointsMemoryStorage {

    private var _pointsCache : List<Point>? = null

    override fun savePoints(points: List<Point>) {
        _pointsCache = points
    }

    override fun getPoints(): List<Point>? {
        return _pointsCache
    }
}