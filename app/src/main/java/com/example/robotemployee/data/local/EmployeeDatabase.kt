package com.example.robotemployee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.robotemployee.model.NewEmployee


@Database(
    entities = [NewEmployee::class], version = 1, exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao


}