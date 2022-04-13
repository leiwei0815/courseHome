package com.example.coursehome.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.coursehome.db.AppDataBase
import com.example.coursehome.entity.courseBrandEntity
import com.example.coursehome.init.AppHelper
import com.example.coursehome.init.isConnectedNetwork

@OptIn(ExperimentalPagingApi::class)
class courseBrandRemoteMediator(
    private val api: courseBrandService,
    private val database: AppDataBase
) : RemoteMediator<Int, courseBrandEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, courseBrandEntity>
    ): MediatorResult {
        try {
            //第一步, 判断 LoadType , 根据LoadType计算page(当前页)
            val pageKey = when (loadType) {
                //首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null
                //在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                //加载更多时触发
                LoadType.APPEND -> {
                    val lastItem: courseBrandEntity =
                        state.lastItemOrNull() ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.page
                }
            }

            //无网络加载本地数据
            if(!AppHelper.mContext.isConnectedNetwork()){
               return MediatorResult.Success(endOfPaginationReached = true)
            }

            //第二步, 请求网络分页数据
            val page = pageKey ?: 0
            val result = api.fetchcourseBrandList((page * state.config.pageSize).toString(), state.config.pageSize.toString()).data.list

            Log.d("httpResult","result:${result.toString()} + $page")
            val item = result.map {
                courseBrandEntity(
                    id = it.goodsCode.toInt(),
                    name = it.goodsName,
                    icon = "${"https://res.shiguangkey.com/" + it.cover}",
                    page = page + 1
                )
            }

            val endOfPaginationReached = result.isEmpty()
            //第三步, 插入数据库
            val courseBrandDao = database.courseBrandDao()
            database.withTransaction {
                if (loadType == LoadType.REFRESH){
                    courseBrandDao.clearcourseBrand()
                }
                courseBrandDao.insertcourseBrand(item)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }

    }

}