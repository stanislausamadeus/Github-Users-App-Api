package com.dicoding.githubusersappapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubusersappapi.data.database.FavoriteUsers
import com.dicoding.githubusersappapi.data.response.ItemResponse
import com.dicoding.githubusersappapi.databinding.ActivityFavoriteUsersBinding
import com.dicoding.githubusersappapi.helper.FavoriteUsersViewModelFactory
import com.dicoding.githubusersappapi.view.adapter.UserAdapter

class FavoriteUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUsersBinding
    private lateinit var viewModel: FavoriteUsersViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        viewModel = ViewModelProvider(this@FavoriteUsersActivity, FavoriteUsersViewModelFactory.getInstance(this@FavoriteUsersActivity.application)).get(FavoriteUsersViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ItemResponse) {
                Intent(this@FavoriteUsersActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_HTML, data.htmlUrl)
                    startActivity(it)
                }
            }
        })

        showRecyclerList()


        viewModel.getAllFavoriteUsers().observe(this) {
            if (it != null) {
                val mapList = mapList(it)
                adapter.setList(mapList)
            }
        }

    }

    private fun mapList(users: List<FavoriteUsers>): ArrayList<ItemResponse> {
        val arrayListUsers = ArrayList<ItemResponse>()
        for (i in users) {
            val mapped = ItemResponse(
                i.avatarUrl, i.id, i.login, i.htmlUrl
            )
            arrayListUsers.add(mapped)
        }
        return arrayListUsers
    }

    private fun showRecyclerList() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)
    }

}