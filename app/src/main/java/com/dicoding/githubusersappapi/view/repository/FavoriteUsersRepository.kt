package com.dicoding.githubusersappapi.view.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.githubusersappapi.data.database.FavoriteUsers
import com.dicoding.githubusersappapi.data.database.FavoriteUsersDao
import com.dicoding.githubusersappapi.data.database.FavoriteUsersDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteUsersRepository(application: Application) {
    private val mFavoriteUsersDao: FavoriteUsersDao

    init {
        val db = FavoriteUsersDatabase.getDatabase(application)
        mFavoriteUsersDao = db.favoriteUsersDao()
    }

    fun insert(id: Int, login: String?, avatarUrl: String?, htmlUrl: String? ) {
        CoroutineScope(Dispatchers.IO).launch {
            var favoriteUser = FavoriteUsers(
                id, login, avatarUrl, htmlUrl
            )
            mFavoriteUsersDao.insert(favoriteUser)
        }
    }

    fun delete(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            mFavoriteUsersDao.delete(id)
        }
    }

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUsers>> = mFavoriteUsersDao.getAllFavoriteUsers()

    suspend fun checkFavorite(id: Int) = mFavoriteUsersDao.checkFavorite(id)
}