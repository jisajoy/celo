package com.interview.challenge.celouser.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interview.challenge.celouser.database.UserDao
import com.interview.challenge.celouser.model.User
import com.interview.challenge.celouser.model.UserListDatabase

class DetailModelFactory(
    private val userDetails: User
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(userDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}