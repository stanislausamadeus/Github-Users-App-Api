package com.dicoding.githubusersappapi.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubusersappapi.api.ApiConfig
import com.dicoding.githubusersappapi.data.ItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<ItemResponse>>()

    fun setListFollowing(username: String) {
        ApiConfig.getApiService()
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<ItemResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemResponse>>,
                    response: Response<ArrayList<ItemResponse>>
                ) {
                    if (response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemResponse>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<ItemResponse>> {
        return listFollowing
    }

    companion object {
        private const val TAG = "FollowingViewModel"
    }
}