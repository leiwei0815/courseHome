package com.example.coursehome.remote

import com.example.paging.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface courseBrandService {

//    @GET("")
//    suspend fun fetchcourseBrandList(
//        @Query("since") since:Int,
//        @Query("pageIndex") pageIndex:Int
//    ):List<courseBrandItemModel>

    @GET("goods/search")
    suspend fun fetchcourseBrandList(
        @Query("pageIndex") pageIndex: String,
        @Query("pageSize") pageSize: String,
        @Query("terminalType") terminalType: String = "2"
    ): Movies
}