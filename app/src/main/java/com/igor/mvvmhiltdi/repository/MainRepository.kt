package com.igor.mvvmhiltdi.repository

import com.igor.mvvmhiltdi.modules.Blog
import com.igor.mvvmhiltdi.retrofit.BlogRetrofit
import com.igor.mvvmhiltdi.retrofit.NetworkMapper
import com.igor.mvvmhiltdi.room.BlogDao
import com.igor.mvvmhiltdi.room.CacheMapper
import com.igor.mvvmhiltdi.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(3000)//Only for example!!! delaying to see the progress bar!!!

        try {
            val networkBlogs = blogRetrofit.getBlogData()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)

            //Iterate over the Blogs list to inser to DB
            for(blog in blogs){
                //Insert the blog object to DB  -> "Cache"
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }

            //Get data from DB -> "Cache"
            val cachedBlogs = blogDao.getBlogs()

            //Emitting data to UI after retrieving from DB
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }


    }
}