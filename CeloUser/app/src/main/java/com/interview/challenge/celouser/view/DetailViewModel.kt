package com.interview.challenge.celouser.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.interview.challenge.celouser.database.UserDao
import com.interview.challenge.celouser.model.DomainModel
import com.interview.challenge.celouser.model.User
import com.interview.challenge.celouser.model.UserListDatabase
import kotlinx.coroutines.*

class DetailViewModel(private val userDetail: User) :
    ViewModel() {

    var data: User = userDetail

}