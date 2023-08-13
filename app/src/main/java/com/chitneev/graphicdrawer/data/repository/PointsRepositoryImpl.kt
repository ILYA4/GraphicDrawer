package com.chitneev.graphicdrawer.data.repository

import com.chitneev.graphicdrawer.data.models.PointDto
import com.chitneev.graphicdrawer.data.service.PointsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PointsRepositoryImpl : PointsRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<PointsService>()

    override suspend fun getPoints(count: Int) : List<PointDto> {
         return service.getPoints(count).points
    }

    companion object {
        private const val BASE_URL = "https://hr-challenge.interactivestandard.com/"
    }
}