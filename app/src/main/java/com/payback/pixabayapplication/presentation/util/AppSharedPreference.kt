package com.payback.pixabayapp.presentation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPreference@Inject constructor(@ApplicationContext context: Context) {

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    private fun editOrCreateString(name: String, value: String) {
        sharedPreferences.edit {
            putString(name, value)
            commit()
        }
    }


    fun saveQuery(value: String) {
        editOrCreateString("query", value)
    }


    fun getQuery(): String {
        return sharedPreferences.getString("query", "fruit")!!
    }
}