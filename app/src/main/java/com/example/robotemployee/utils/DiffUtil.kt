package com.example.robotemployee.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.robotemployee.model.EmployeeUI

class DiffUtil(
    private val oldList: List<EmployeeUI>,
    private val newList: List<EmployeeUI>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uuid == newList[newItemPosition].uuid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return when {

            oldList[oldItemPosition].uuid != newList[newItemPosition].uuid -> false

            oldList[oldItemPosition].fullName != newList[newItemPosition].fullName -> false

            oldList[oldItemPosition].dob != newList[newItemPosition].dob -> false

            oldList[oldItemPosition].photoUrlSmall != newList[newItemPosition].photoUrlSmall -> false

            else -> true

        }

    }
}