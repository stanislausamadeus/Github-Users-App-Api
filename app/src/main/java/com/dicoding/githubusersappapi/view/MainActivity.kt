package com.dicoding.githubusersappapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubusersappapi.databinding.ActivityMainBinding
import com.dicoding.githubusersappapi.view.adapter.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.rvUser.setHasFixedSize(true)

        showRecyclerList()

        binding.btnSearch.setOnClickListener {
            searchUser()
        }

        viewModel.getSearch().observe(this, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter
    }

    private fun searchUser() {
        val query = binding.search.text.toString()
        if (query.isEmpty()) return
        showLoading(true)
        viewModel.setSearch(query)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}