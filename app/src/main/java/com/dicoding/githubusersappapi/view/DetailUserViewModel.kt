package com.dicoding.githubusersappapi.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubusersappapi.api.ApiConfig
import com.dicoding.githubusersappapi.data.database.FavoriteUsers
import com.dicoding.githubusersappapi.data.response.DetailResponse
import com.dicoding.githubusersappapi.view.repository.FavoriteUsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): ViewModel() {
    private val mFavoriteUsersRepository: FavoriteUsersRepository = FavoriteUsersRepository(application)

    val detailUser = MutableLiveData<DetailResponse>()

    fun setDetailUser(username: String) {
        ApiConfig.getApiService()
            .getDetailUser(username)
            .enqueue(object : Callback<DetailResponse>{
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful) {
                        detailUser.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }

            })
    }
    fun getDetailUser(): LiveData<DetailResponse>{
        return detailUser
    }

    fun insert(id: Int, login: String?, avatarUrl: String?, htmlUrl: String?) {
        mFavoriteUsersRepository.insert(id, login, avatarUrl, htmlUrl)
    }

    fun delete(id: Int) {
        mFavoriteUsersRepository.delete(id)
    }

    suspend fun checkFavorite(id: Int) = mFavoriteUsersRepository.checkFavorite(id)

    companion object {
        private const val TAG = "DetailUserViewModel"
    }
}