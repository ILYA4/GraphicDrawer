package com.chitneev.graphicdrawer.domain.usecase

import com.chitneev.graphicdrawer.data.repository.PointsRepository
import javax.inject.Inject

class FetchPointsUseCase @Inject constructor(
    private val repository: PointsRepository,
) {

    suspend operator fun invoke(countOfPoints: Int) {
        repository.fetchPoints(countOfPoints)
    }

}