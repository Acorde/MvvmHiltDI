package com.igor.mvvmhiltdi.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getBlogData(): List<BlogNetworkEntity>

}