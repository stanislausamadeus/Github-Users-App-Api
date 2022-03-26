package com.dicoding.githubusersappapi.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubusersappapi.view.DetailUserViewModel

class DetailUserViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: DetailUserViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): DetailUserViewModelFactory {
            if (INSTANCE == null) {
                synchronized(DetailUserViewModelFactory::class.java) {
                    INSTANCE = DetailUserViewModelFactory(application)
                }
            }
            return INSTANCE as DetailUserViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}