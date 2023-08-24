package com.chitneev.graphicdrawer.di

import com.chitneev.graphicdrawer.data.repository.PointsRepository
import com.chitneev.graphicdrawer.data.repository.PointsRepositoryImpl
import com.chitneev.graphicdrawer.data.storage.PointsMemoryStorage
import com.chitneev.graphicdrawer.data.storage.PointsMemoryStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providePointsRepository(memoryStorage: PointsMemoryStorage): PointsRepository {
        return PointsRepositoryImpl(
            defaultDispatcher = Dispatchers.IO,
            memoryStorage = memoryStorage
        )
    }

    @Singleton
    @Provides
    fun provideMemoryStorage(): PointsMemoryStorage {
        return PointsMemoryStorageImpl()
    }
}
