package com.example.robotemployee.extensions

import com.example.robotemployee.model.EmployeeUI
import com.example.robotemployee.model.NewEmployee


fun NewEmployee.toListUiEmployee(): EmployeeUI {

    return EmployeeUI(
        uuid = this.uid,
        emailAddress = this.email,
        fullName = "${this.firstName} ${this.lastName}",
        photoUrlSmall = this.avatar,
        dob = this.dob,

    )

}


