package com.dicoding.githubusersappapi.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubusersappapi.data.database.FavoriteUsers
import com.dicoding.githubusersappapi.view.repository.FavoriteUsersRepository

class FavoriteUsersViewModel(application: Application) : ViewModel() {
    private val mFavoriteUsersRepository: FavoriteUsersRepository = FavoriteUsersRepository(application)

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUsers>> {
        return mFavoriteUsersRepository.getAllFavoriteUsers()
    }
}