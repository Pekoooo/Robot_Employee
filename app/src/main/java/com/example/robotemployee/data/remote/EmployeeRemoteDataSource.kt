package com.example.robotemployee.data.remote

import com.example.robotemployee.model.FakeUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRemoteDataSource @Inject constructor(
    private val employeeJsonHelper: EmployeeJsonHelper
) {

    suspend fun getEmployeeList(): FakeUserResponse =
        withContext(Dispatchers.IO) {
            employeeJsonHelper.getEmployees()
        }
}