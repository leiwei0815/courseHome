package com.example.coursehome.repository

import androidx.paging.PagingData
import com.example.coursehome.model.CourseBrandItemModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchcourseBrandList(): Flow<PagingData<CourseBrandItemModel>>
}