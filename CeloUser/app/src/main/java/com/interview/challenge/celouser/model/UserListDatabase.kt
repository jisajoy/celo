package com.interview.challenge.celouser.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class UserListDatabase constructor(
    @PrimaryKey
    val cell: String,
    val dob: String,
    val age: Int,
    val email: String,
    val gender: String,
    val location: String,
    var login: String,
    var name: String,
    var phone: String,
    var picture_large: String,
    var picture_medium: String
)

/*
fun List<UserListDatabase>.asDomainModel(): List<DomainModel> {
    return map {
        DomainModel(
            cell = it.cell,
            dob = it.dob,
            age = it.age,
            email = it.email,
            gender = it.gender,
            location = it.location,
            login = it.login,
            name = it.name,
            phone = it.phone,
            picture_large = it.picture_large,
            picture_medium = it.picture_medium)
    }
}*/
