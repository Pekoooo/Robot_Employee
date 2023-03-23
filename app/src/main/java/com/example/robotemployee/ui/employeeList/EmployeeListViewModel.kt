package com.example.robotemployee.ui.employeeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.robotemployee.domain.GetAllEmployees
import com.example.robotemployee.model.EmployeeUI
import com.example.robotemployee.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val getAllEmployees: GetAllEmployees,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _uiState = MutableStateFlow<EmployeeListState>(EmployeeListState.Loading)
    val uiState: StateFlow<EmployeeListState> = _uiState


    init {
        fetchAllEmployees()
    }


    fun fetchAllEmployees(forceEndpointCall: Boolean = false) = viewModelScope.launch(dispatcher) {

        getAllEmployees.employeesForListUi(forceEndpointCall).collect { result ->
            when (result) {
                is Result.Error -> handleError(result.error)
                is Result.Success -> {
                    _uiState.value = if (result.data.isEmpty()) {
                        EmployeeListState.Error(EmployeeListState.ErrorType.EmptyList)
                    } else {
                        val sortedList = result.data.sortedBy { it.fullName }
                        EmployeeListState.Success(sortedList)
                    }
                }
            }
        }
    }

    private fun handleError(error: Throwable) {
        _uiState.value = when (error) {
            is UnknownHostException -> EmployeeListState.Error(EmployeeListState.ErrorType.NetworkError)
            is java.lang.NullPointerException -> {
                EmployeeListState.Error(EmployeeListState.ErrorType.MalformedEmployee)
            }
            else -> EmployeeListState.Error(EmployeeListState.ErrorType.Other(error.message))

        }
    }
}

sealed class EmployeeListState {
    object Loading : EmployeeListState()
    data class Success(val employeeListUi: List<EmployeeUI>) : EmployeeListState()
    data class Error(val errorType: ErrorType) : EmployeeListState()

    sealed class ErrorType {
        object NetworkError : ErrorType()
        object MalformedEmployee : ErrorType()
        object EmptyList : ErrorType()
        data class Other(val message: String? = null) : ErrorType()
    }
}