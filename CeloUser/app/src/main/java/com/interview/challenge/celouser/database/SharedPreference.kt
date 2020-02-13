package com.interview.challenge.celouser.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val PREFERENCE_FILE_KEY = "celo_users_data"
    private var PAGE_NUMBER = 1
    private val PAGE_NUMBER_KEY = "page_number"

    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    fun savePageNumber(number: Int) {
        with(sharedPref.edit()) {
            putInt(PAGE_NUMBER_KEY, number)
            commit()
        }
    }

    fun getPageNumber(): Int {
        return sharedPref.getInt(PAGE_NUMBER_KEY, 1)
    }
}