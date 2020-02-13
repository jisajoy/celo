package com.interview.challenge.celouser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val cell: String?,
    val dob: String?,
    val age: Int,
    val email: String?,
    val gender: String?,
    val location: String?,
    val login: String?,
    val name: String?,
    val phone: String?,
    val picture_large: String?,
    val picture_medium: String?
) : Parcelable