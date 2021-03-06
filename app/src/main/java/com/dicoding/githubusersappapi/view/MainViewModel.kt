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

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<ItemResponse>>()

    companion object {
        private const val TAG = "MainViewModel"
    }

    fun setSearch(query: String){
        ApiConfig.getApiService()
            .getSearch(query)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun getSearch(): LiveData<ArrayList<ItemResponse>>{ return listUsers }
}