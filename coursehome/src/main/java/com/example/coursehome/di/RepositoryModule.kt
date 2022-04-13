package com.example.coursehome.di

import android.app.Application
import androidx.room.Room
import com.example.coursehome.db.AppDataBase
import com.example.coursehome.db.courseBrandDao
import com.example.coursehome.mapper.Entity2ItemModelMapper
import com.example.coursehome.remote.courseBrandService
import com.example.coursehome.repository.courseBrandRepositoryImpl
import com.example.coursehome.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class RepositoryModule {

    @ActivityScoped
    @Provides
    fun providecourseBrandRepository(api: courseBrandService,dataBase: AppDataBase): Repository {
        return courseBrandRepositoryImpl(api,dataBase,Entity2ItemModelMapper())
    }
}