package com.example.robotemployee.data.local

import com.example.robotemployee.model.NewEmployee
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EmployeeLocalDataSource @Inject constructor(
    private val employeeDao: EmployeeDao
) {

    fun getEmployees(): Flow<List<NewEmployee>> {
        return employeeDao.getEmployees()
    }

    fun insertEmployees(oldEmployeeList: List<NewEmployee>) {
        employeeDao.insertEmployees(*oldEmployeeList.toTypedArray())
    }

    fun dropTable() {
        employeeDao.dropTable()
    }


}