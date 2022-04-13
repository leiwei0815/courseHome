package com.example.coursehome.di

import android.app.Application
import androidx.room.Room
import com.example.coursehome.db.AppDataBase
import com.example.coursehome.db.courseBrandDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideAppData(application: Application):AppDataBase{
        return Room.databaseBuilder(application,AppDataBase::class.java,"course_brand_db").build()
    }

    @Singleton
    @Provides
    fun providecourseBrandDao(dataBase: AppDataBase):courseBrandDao{
        return dataBase.courseBrandDao()
    }
}