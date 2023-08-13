package com.chitneev.graphicdrawer.domain.usecase

import com.chitneev.graphicdrawer.data.models.PointDto
import com.chitneev.graphicdrawer.data.repository.PointsRepositoryImpl
import com.chitneev.graphicdrawer.domain.models.Point

class FetchPointsUseCase {

    private val repository = PointsRepositoryImpl()

    suspend operator fun invoke(countOfPoints: Int) : List<Point> {
        return repository.getPoints(countOfPoints)
            .toDomain()
    }

    private fun List<PointDto>.toDomain() : List<Point> {
        return map { Point(it.x, it.y) }
    }
}