package com.igor.mvvmhiltdi.di

import com.igor.mvvmhiltdi.repository.MainRepository
import com.igor.mvvmhiltdi.retrofit.BlogRetrofit
import com.igor.mvvmhiltdi.retrofit.NetworkMapper
import com.igor.mvvmhiltdi.room.BlogDao
import com.igor.mvvmhiltdi.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}