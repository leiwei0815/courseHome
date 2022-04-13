package com.example.coursehome.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coursehome.entity.courseBrandEntity

@Database(
    entities = [courseBrandEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun courseBrandDao() : courseBrandDao
}