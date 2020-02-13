package com.interview.challenge.celouser.model


data class DomainModel(
    val cell: String,
    val dob: String,
    val age: Int,
    val email: String,
    val gender: String,
    val location: String,
    val login: String,
    val name: String,
    val phone: String,
    val picture_large: String,
    val picture_medium: String
)


/**
 * Short description is used for displaying truncated descriptions in the UI
 */
/* val shortDescription: String
     get() = description.smartTruncate(200)*/