package com.chitneev.graphicdrawer.domain.usecase

import com.chitneev.graphicdrawer.data.models.PointDto
import com.chitneev.graphicdrawer.data.repository.PointsRepository
import com.chitneev.graphicdrawer.domain.models.Point
import javax.inject.Inject

class FetchPointsUseCase @Inject constructor(
    private val repository: PointsRepository,
) {

    suspend operator fun invoke(countOfPoints: Int) : List<Point> {
        return repository.getPoints(countOfPoints)
            .toDomain()
    }

    private fun List<PointDto>.toDomain() : List<Point> {
        return map { Point(it.x, it.y) }
    }
}