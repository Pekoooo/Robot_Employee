package com.example.robotemployee.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "employee_table")
data class NewEmployee(
    @Expose
    @SerializedName("avatar")
    val avatar: String,
    @Expose
    @SerializedName("date_of_birth")
    val dob: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("first_name")
    val firstName: String,
    @Expose
    @SerializedName("gender")
    val gender: String,
    @PrimaryKey(autoGenerate = false)
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("last_name")
    val lastName: String,
    @Expose
    @SerializedName("password")
    val password: String,
    @Expose
    @SerializedName("phone_number")
    val phoneNumber: String,
    @Expose
    @SerializedName("social_insurance_number")
    val socialInsuranceNumber: String,
    @Expose
    @SerializedName("uid")
    val uid: String,
    @Expose
    @SerializedName("username")
    val username: String
)