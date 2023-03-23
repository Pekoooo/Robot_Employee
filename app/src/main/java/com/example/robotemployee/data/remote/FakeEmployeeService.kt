package com.example.robotemployee.data.remote

import com.example.robotemployee.model.FakeUserResponse
import retrofit2.http.GET


interface FakeEmployeeService {

    @GET("/api/v2/users?size=10")
    suspend fun getEmployeeList(): FakeUserResponse

}