package com.example.robotemployee.ui.employeeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.robotemployee.R
import com.example.robotemployee.databinding.EmployeeRowItemBinding
import com.example.robotemployee.model.EmployeeUI
import com.example.robotemployee.utils.picassoTransformations.CircleTransform
import com.squareup.picasso.Picasso
import com.example.robotemployee.utils.DiffUtil as MyDiffUtil


class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    private var oldEmployeeList = emptyList<EmployeeUI>()


    class ViewHolder(val binding: EmployeeRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EmployeeRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentEmployee = oldEmployeeList[position]

        holder.binding.employeeListEmail.text = currentEmployee.emailAddress
        holder.binding.employeeListName.text = currentEmployee.fullName
        holder.binding.employeeListTeam.text = currentEmployee.dob

        Picasso.get()
            .load(currentEmployee.photoUrlSmall)
            .transform(CircleTransform())
            .placeholder(R.drawable.empty_profile_picture)
            .into(holder.binding.employeeListProfilePicture)
    }

    override fun getItemCount(): Int {
        return oldEmployeeList.size
    }

    fun setData(newEmployeeList: List<EmployeeUI>) {
        val diffUtil = MyDiffUtil(oldEmployeeList, newEmployeeList)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        oldEmployeeList = newEmployeeList
        diffUtilResults.dispatchUpdatesTo(this)
    }
}