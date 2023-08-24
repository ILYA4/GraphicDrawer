package com.chitneev.graphicdrawer.data.repository

import com.chitneev.graphicdrawer.data.models.PointDto
import com.chitneev.graphicdrawer.data.service.PointsService
import com.chitneev.graphicdrawer.data.storage.PointsMemoryStorage
import com.chitneev.graphicdrawer.domain.models.Point
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.IllegalStateException
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher,
    private val memoryStorage: PointsMemoryStorage,
) : PointsRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<PointsService>()

    override suspend fun fetchPoints(count: Int): List<Point> =
        withContext(defaultDispatcher) {
            return@withContext service
                .getPoints(count).points
                .toDomain()
                .also { points ->
                    val sortedPoints = points.sortedBy { it.x }
                    memoryStorage.savePoints(sortedPoints)
                }
        }

    override fun getCachedPoints(): List<Point> {
        return memoryStorage.getPoints()
            ?: throw IllegalStateException("Points cannot be null if you request on the graph screen")
    }

    private fun List<PointDto>.toDomain(): List<Point> {
        return map { Point(it.x, it.y) }
    }

    companion object {
        private const val BASE_URL = "https://hr-challenge.interactivestandard.com/"
    }
}