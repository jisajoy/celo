/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.interview.challenge.celouser.database

import android.content.Context
import androidx.paging.DataSource
import androidx.room.*
import com.interview.challenge.celouser.model.UserListDatabase


@Database(entities = [UserListDatabase::class], version = 1)
abstract class VideosDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}

@Dao
interface UserDao {
    @Query("select * from userlistdatabase")
    fun getUserList(): DataSource.Factory<Int, UserListDatabase>
    //fun getUserList(): LiveData<List<UserListDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUserList(videos: List<UserListDatabase>)

}

private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {
    synchronized(VideosDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VideosDatabase::class.java,
                "celo_users"
            ).build()
        }
    }
    return INSTANCE
}