package com.interview.challenge.celouser.adapter

import android.app.Application
import android.util.Log
import androidx.paging.PagedList
import com.interview.challenge.celouser.database.SharedPreference
import com.interview.challenge.celouser.database.VideosDatabase
import com.interview.challenge.celouser.model.UserListDatabase
import com.interview.challenge.celouser.model.asDatabaseModel
import com.interview.challenge.celouser.network.CeloUserNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class BounderyCallback(
    val viewModelScope: CoroutineScope,
    val database: VideosDatabase,
    application: Application
) : PagedList.BoundaryCallback<UserListDatabase>() {
    
    private var resultNumber = 1000
    private var sharedPref: SharedPreference = SharedPreference(application)

    override fun onZeroItemsLoaded() {
        loadDataFromNetwork()
    }

    override fun onItemAtEndLoaded(itemAtEnd: UserListDatabase) {
        loadDataFromNetwork()
    }

    private fun loadDataFromNetwork() {
        var pageNumber = sharedPref.getPageNumber()
        viewModelScope.launch {
            try {
                refreshVideos(pageNumber, resultNumber)
                sharedPref.savePageNumber(pageNumber + 1)
            } catch (networkError: IOException) {
                Log.d("error", networkError.message)
            }
        }
    }

    suspend fun refreshVideos(pageNumber: Int, resultNumber: Int) {
        withContext(Dispatchers.IO) {
            val userList =
                CeloUserNetwork.services.getNetworkUsersList(pageNumber, resultNumber).await()
            database.userDao.insertAllUserList(userList.asDatabaseModel())
        }
    }
}