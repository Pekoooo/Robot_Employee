package com.example.robotemployee.data

import com.example.robotemployee.data.local.EmployeeLocalDataSource
import com.example.robotemployee.data.remote.EmployeeRemoteDataSource
import com.example.robotemployee.model.NewEmployee
import com.example.robotemployee.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class EmployeeRepository @Inject constructor(
    private val localDataSource: EmployeeLocalDataSource,
    private val remoteDataSource: EmployeeRemoteDataSource
) {


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getEmployees(forceEndpointCall: Boolean): Flow<Result<List<NewEmployee>>> {

        return if (forceEndpointCall) {
            getEmployeesFromRemoteDataSource()
        } else {
            getEmployeesFromLocalDataSource().flatMapLatest { result ->
                if (result is Result.Success && result.data.isNotEmpty()) {
                    flowOf(result)
                } else {
                    getEmployeesFromRemoteDataSource()
                }
            }
        }

    }


    private fun getEmployeesFromLocalDataSource(): Flow<Result<List<NewEmployee>>> {
        return try {
            localDataSource.getEmployees().map {
                Result.Success(it)
            }
        } catch (e: Exception) {
            flowOf(Result.Error(e))
        }
    }

    private suspend fun getEmployeesFromRemoteDataSource(): Flow<Result<List<NewEmployee>>> {
        return try {
            val employeeListRaw =
                remoteDataSource.getEmployeeList()
            localDataSource.dropTable()
            localDataSource.insertEmployees(employeeListRaw)
            flowOf(Result.Success(employeeListRaw))
        } catch (e: Exception) {
            flowOf(Result.Error(e))
        }
    }


}