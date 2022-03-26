package com.dicoding.githubusersappapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubusersappapi.R
import com.dicoding.githubusersappapi.databinding.FragmentFollowerBinding
import com.dicoding.githubusersappapi.view.adapter.UserAdapter

class FollowersFragment : Fragment(R.layout.fragment_follower) {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        _binding = FragmentFollowerBinding.bind(view)

        adapter = UserAdapter()

        showRecyclerList()
        showLoading(true)

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerList() {
        binding.rvFollower.layoutManager = LinearLayoutManager(activity)
        binding.rvFollower.adapter = adapter
        binding.rvFollower.setHasFixedSize(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}