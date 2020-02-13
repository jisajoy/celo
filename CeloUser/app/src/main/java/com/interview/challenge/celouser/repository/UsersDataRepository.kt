package com.interview.challenge.celouser.repository

import android.app.Application
import androidx.paging.LivePagedListBuilder
import com.interview.challenge.celouser.adapter.BounderyCallback
import com.interview.challenge.celouser.database.VideosDatabase
import kotlinx.coroutines.CoroutineScope

class UsersDataRepository(
    private val database: VideosDatabase,
    viewModelScope: CoroutineScope,
    application: Application
) {

    private val dataSourceFactory = database.userDao.getUserList()
    private val boundaryCallBack = BounderyCallback(viewModelScope, database, application)
    val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
        .setBoundaryCallback(boundaryCallBack)
        .build()

    companion object {
        private const val DATABASE_PAGE_SIZE = 100
    }
}
