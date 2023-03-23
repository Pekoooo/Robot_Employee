package com.example.robotemployee.module

import android.content.Context
import androidx.room.Room
import com.example.robotemployee.data.local.EmployeeDao
import com.example.robotemployee.data.local.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideEmployeeDao(database: EmployeeDatabase): EmployeeDao {
        return database.employeeDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): EmployeeDatabase {
        return Room.databaseBuilder(
            context,
            EmployeeDatabase::class.java,
            "employee_database"
        ).build()
    }
}