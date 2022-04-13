package com.example.coursehome.repository

import androidx.paging.*
import com.example.coursehome.db.AppDataBase
import com.example.coursehome.entity.courseBrandEntity
import com.example.coursehome.mapper.Mapper
import com.example.coursehome.model.CourseBrandItemModel

import com.example.coursehome.remote.courseBrandRemoteMediator
import com.example.coursehome.remote.courseBrandService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class courseBrandRepositoryImpl(
    private val api: courseBrandService,
    private val database: AppDataBase,
    private val mapper2ItemModel: Mapper<courseBrandEntity, CourseBrandItemModel>
) : Repository {
    @ExperimentalPagingApi
    override fun fetchcourseBrandList(): Flow<PagingData<CourseBrandItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 20,
                prefetchDistance = 1
            ),
            remoteMediator = courseBrandRemoteMediator(api, database) // 请求网络数据, 放入数据库
        ) {
            database.courseBrandDao().getcourseBrand()
        }.flow
            .flowOn(Dispatchers.IO).map { PagingData ->
                PagingData.map { mapper2ItemModel.map(it) } //对数据进行转换, 给到UI显示
            }
    }

}