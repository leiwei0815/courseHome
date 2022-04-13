package com.example.coursehome.di

import android.util.Log
import com.example.coursehome.init.BASEURL
import com.example.coursehome.remote.courseBrandService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetWorkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        val logging = HttpLoggingInterceptor{
            Log.d("xiaowei",it)
        }
        logging.level = HttpLoggingInterceptor.Level.BODY
       return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providecourseBrandService(retrofit: Retrofit):courseBrandService{
        return retrofit.create(courseBrandService::class.java)
    }
}