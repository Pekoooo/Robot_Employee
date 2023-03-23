package com.example.robotemployee.data.local

import androidx.room.*
import com.example.robotemployee.model.NewEmployee
import kotlinx.coroutines.flow.Flow


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee_table")
    fun getEmployees(): Flow<List<NewEmployee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(vararg oldEmployee: NewEmployee)

    @Query("DELETE FROM employee_table")
    fun dropTable()

}