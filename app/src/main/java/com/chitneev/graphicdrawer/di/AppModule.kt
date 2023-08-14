package com.chitneev.graphicdrawer.di

import com.chitneev.graphicdrawer.data.repository.PointsRepository
import com.chitneev.graphicdrawer.data.repository.PointsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providePointsRepository(): PointsRepository {
        return PointsRepositoryImpl()
    }
}
