package com.chitneev.graphicdrawer.domain.usecase

import com.chitneev.graphicdrawer.data.repository.PointsRepository
import com.chitneev.graphicdrawer.domain.models.Point
import javax.inject.Inject

class GetCachedPointsUseCase @Inject constructor(
    private val repository: PointsRepository,
) {

    operator fun invoke(): List<Point> =
        repository.getCachedPoints()

}