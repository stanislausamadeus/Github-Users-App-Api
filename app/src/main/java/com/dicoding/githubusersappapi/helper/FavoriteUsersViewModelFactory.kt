package com.dicoding.githubusersappapi.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubusersappapi.view.FavoriteUsersViewModel

class FavoriteUsersViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: FavoriteUsersViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavoriteUsersViewModelFactory {
            if (INSTANCE == null) {
                synchronized(FavoriteUsersViewModelFactory::class.java) {
                    INSTANCE = FavoriteUsersViewModelFactory(application)
                }
            }
            return INSTANCE as FavoriteUsersViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUsersViewModel::class.java)) {
            return FavoriteUsersViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}