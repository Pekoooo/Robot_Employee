package com.example.robotemployee.ui.employeeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.robotemployee.databinding.FragmentEmployeeListBinding
import com.example.robotemployee.R
import com.example.robotemployee.model.EmployeeUI
import com.example.robotemployee.ui.MainActivity
import com.example.robotemployee.utils.Constants.TAG
import com.example.robotemployee.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { EmployeeListAdapter() }
    private val viewModel: EmployeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)

        setToolbarTitle()
        setSwipeLayout()
        setRecyclerView()
        setUpObservers()

        return binding.root
    }

    private fun setUpObservers() {
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect { employeeListState ->

                    binding.progressBar.visibility =
                        if (employeeListState is EmployeeListState.Loading) View.VISIBLE else View.GONE

                    when (employeeListState) {
                        is EmployeeListState.Error -> showError(employeeListState.errorType)
                        is EmployeeListState.Loading -> showProgressBar()
                        is EmployeeListState.Success -> loadSuccessUi(employeeListState.employeeListUi)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun showError(errorType: EmployeeListState.ErrorType) {
        Log.d(TAG, "showError: is called $errorType")

        setListVisibility()

        when (errorType) {

            EmployeeListState.ErrorType.EmptyList -> {
                binding.employeeListFragmentLayout.showSnackBar(R.string.empty_list_error_snackbar)
                setEmptyListVisibility()
            }

            EmployeeListState.ErrorType.MalformedEmployee -> {
                binding.employeeListFragmentLayout.showSnackBar(R.string.data_corrupted_error_snackbar)
                setEmptyListVisibility()

            }

            EmployeeListState.ErrorType.NetworkError ->
                binding.employeeListFragmentLayout.showSnackBar(R.string.network_error_snackbar)

            is EmployeeListState.ErrorType.Other ->
                binding.employeeListFragmentLayout.showSnackBar(
                    errorType.message ?: getString(R.string.empty_list_error_snackbar)
                )
            else -> {}
        }
    }


    private fun loadSuccessUi(employeeList: List<EmployeeUI>) {
        setListVisibility()
        adapter.setData(employeeList)
    }

    private fun setListVisibility() {
        binding.mainEmployeeRecyclerview.visibility = View.VISIBLE
        binding.noEmployeesImageView.visibility = View.GONE
        binding.noEmployeesTextMessage.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.mainEmployeeRecyclerview.visibility = View.GONE
        binding.noEmployeesImageView.visibility = View.GONE
        binding.noEmployeesTextMessage.visibility = View.GONE
    }

    private fun setEmptyListVisibility() {
        binding.mainEmployeeRecyclerview.visibility = View.GONE
        binding.noEmployeesImageView.visibility = View.VISIBLE
        binding.noEmployeesTextMessage.visibility = View.VISIBLE
    }

    private fun setSwipeLayout() {
        binding.swipeRefreshLayoutMainRecyclerview.setOnRefreshListener {
            viewModel.fetchAllEmployees(forceEndpointCall = true)
            binding.swipeRefreshLayoutMainRecyclerview.isRefreshing = false
        }
    }

    private fun setToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_employee_list_title)
    }

    private fun setRecyclerView() {
        binding.mainEmployeeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.mainEmployeeRecyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}