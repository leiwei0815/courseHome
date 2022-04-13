package com.example.coursehome.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class courseBrandEntity(

    @PrimaryKey
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "page")
    val page: Int=0, //页码
    @ColumnInfo(name = "icon")
    val icon:String
)
