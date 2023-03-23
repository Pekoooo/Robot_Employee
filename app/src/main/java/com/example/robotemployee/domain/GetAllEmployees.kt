package com.example.robotemployee.domain

import com.example.robotemployee.data.EmployeeRepository
import com.example.robotemployee.extensions.toListUiEmployee
import com.example.robotemployee.model.EmployeeUI
import com.example.robotemployee.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetAllEmployees @Inject constructor(

    private val employeeRepository: EmployeeRepository

) {

    suspend fun employeesForListUi(forceEndpointCall: Boolean = false): Flow<Result<List<EmployeeUI>>> {

        return employeeRepository.getEmployees(forceEndpointCall).map { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data.map { it.toListUiEmployee() })
                is Result.Error -> Result.Error(result.error)
            }
        }
    }
}