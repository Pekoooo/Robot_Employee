package com.example.robotemployee.module

import com.example.robotemployee.BuildConfig
import com.example.robotemployee.data.remote.EmployeeJsonHelper
import com.example.robotemployee.data.remote.EmployeeJsonImpl
import com.example.robotemployee.data.remote.FakeEmployeeService
import com.example.robotemployee.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideSquareEmployeeService(retrofit: Retrofit): FakeEmployeeService = retrofit.create(
        FakeEmployeeService::class.java)

    @Provides
    @Singleton
    fun provideEmployeeJsonHelper(apiHelper: EmployeeJsonImpl): EmployeeJsonHelper = apiHelper





}