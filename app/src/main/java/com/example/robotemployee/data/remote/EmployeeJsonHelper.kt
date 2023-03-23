package com.example.robotemployee.data.remote

import com.example.robotemployee.model.FakeUserResponse


interface EmployeeJsonHelper {

    suspend fun getEmployees(): FakeUserResponse

}