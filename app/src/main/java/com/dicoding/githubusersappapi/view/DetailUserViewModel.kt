package com.dicoding.githubusersappapi.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubusersappapi.api.ApiConfig
import com.dicoding.githubusersappapi.data.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
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

    companion object {
        private const val TAG = "DetailUserViewModel"
    }
}