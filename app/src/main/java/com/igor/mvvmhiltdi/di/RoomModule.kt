package com.igor.mvvmhiltdi.di

import android.content.Context
import androidx.room.Room
import com.igor.mvvmhiltdi.room.BlogDao
import com.igor.mvvmhiltdi.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Use SingletonComponent::class instead of @InstallIn(ApplicationComponent::class)
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(context, BlogDatabase::class.java, BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }

}