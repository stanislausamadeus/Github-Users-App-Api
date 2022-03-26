package com.dicoding.githubusersappapi.view

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.githubusersappapi.api.ApiConfig
import com.dicoding.githubusersappapi.data.preferences.SettingPreferences
import com.dicoding.githubusersappapi.data.response.ItemResponse
import com.dicoding.githubusersappapi.data.response.SearchResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<ItemResponse>>()

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

    companion object {
        private const val TAG = "MainViewModel"
    }
}