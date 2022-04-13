package com.example.coursehome.db


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coursehome.entity.courseBrandEntity

@Dao
interface courseBrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertcourseBrand(courseBrandList: List<courseBrandEntity>)

    @Query("SELECT * FROM courseBrandEntity")
    fun getcourseBrand():PagingSource<Int,courseBrandEntity>

    @Query("DELETE FROM courseBrandEntity")
     fun clearcourseBrand()
}
