package com.dicoding.githubusersappapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.githubusersappapi.R
import com.dicoding.githubusersappapi.databinding.ActivityDetailUserBinding
import com.dicoding.githubusersappapi.view.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setDetailUser(username)
        }
        viewModel.getDetailUser().observe(this) {
            if (it != null) {
                binding.apply {
                    detailUsername.text = it.login
                    detailName.text = it.name
                    detailCompany.text = it.company
                    detailLocation.text = it.location
                    detailFollower.text = it.followers.toString()
                    detailFollowing.text = it.following.toString()
                    detailRepositories.text = it.publicRepos.toString()
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .circleCrop()
                        .into(detailAvatar)
                    showLoading(false)
                }
            }
        }

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle )
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.textfollower, R.string.textfollowing)
    }
}