package com.igor.mvvmhiltdi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity) : Long

    @Query(value = "SELECT * FROM blogs")
    suspend fun getBlogs() : List<BlogCacheEntity>
}