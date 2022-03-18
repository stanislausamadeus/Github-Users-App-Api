package com.dicoding.githubusersappapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setDetailUser(username)
        }
        viewModel.getDetailUser().observe(this) {
            if (it != null) {
                binding.detailUsername.text = it.login
                binding.detailName.text = it.name
                binding.detailCompany.text = it.company
                binding.detailLocation.text = it.location
                binding.detailFollower.text = it.followers.toString()
                binding.detailFollowing.text = it.following.toString()
                binding.detailRepositories.text = it.publicRepos.toString()
                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .into(binding.detailAvatar)
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

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.textfollower, R.string.textfollowing)
    }
}