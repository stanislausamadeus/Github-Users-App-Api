package com.dicoding.githubusersappapi.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubusersappapi.api.ApiConfig
import com.dicoding.githubusersappapi.data.ItemResponse
import com.dicoding.githubusersappapi.data.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<ItemResponse>>()

    companion object {
        private const val TAG = "MainViewModel"
    }

    fun setListFollowers(query: String){
        ApiConfig.getApiService()
            .getFollowers(query)
            .enqueue(object : Callback<ArrayList<ItemResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemResponse>>,
                    response: Response<ArrayList<ItemResponse>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemResponse>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<ItemResponse>> { return listFollowers }
}