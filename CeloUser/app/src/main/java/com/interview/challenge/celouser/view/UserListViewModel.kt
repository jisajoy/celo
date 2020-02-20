package com.interview.challenge.celouser.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.interview.challenge.celouser.database.getDatabase
import com.interview.challenge.celouser.model.DomainModel
import com.interview.challenge.celouser.model.User
import com.interview.challenge.celouser.model.UserListDatabase
import com.interview.challenge.celouser.repository.UsersDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class UserListViewModel(application: Application) : ViewModel() {

    private val viewModelJob = SupervisorJob()


    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)


    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val _navigateToDetail = MutableLiveData<User>()
    val navigateToDetail
        get() = _navigateToDetail

    private val database = getDatabase(application)
    private val videosRepository = UsersDataRepository(database, viewModelScope, application)
    val userList = videosRepository.data


    fun onUserCardClicked(user: User) {
        _navigateToDetail.value = user
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onSleepDetailNavigated() {
        _navigateToDetail.value = null
    }


}