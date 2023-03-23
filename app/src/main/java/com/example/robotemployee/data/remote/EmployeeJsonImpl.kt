package com.example.robotemployee.data.remote

import com.example.robotemployee.model.FakeUserResponse
import javax.inject.Inject

class EmployeeJsonImpl @Inject constructor(
    private val fakeEmployeeService: FakeEmployeeService
): EmployeeJsonHelper {


    override suspend fun getEmployees(): FakeUserResponse =
        fakeEmployeeService.getEmployeeList()

}