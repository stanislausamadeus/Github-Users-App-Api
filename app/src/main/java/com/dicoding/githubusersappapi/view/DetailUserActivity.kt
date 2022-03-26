package com.dicoding.githubusersappapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.githubusersappapi.R
import com.dicoding.githubusersappapi.data.database.FavoriteUsers
import com.dicoding.githubusersappapi.databinding.ActivityDetailUserBinding
import com.dicoding.githubusersappapi.helper.DetailUserViewModelFactory
import com.dicoding.githubusersappapi.view.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        val html = intent.getStringExtra(EXTRA_HTML)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)

        viewModel = ViewModelProvider(this@DetailUserActivity, DetailUserViewModelFactory.getInstance(this@DetailUserActivity.application)).get(DetailUserViewModel::class.java)

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

        var isFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkFavorite(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                        isFavorite = true
                    } else {
                        binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        isFavorite = false
                    }
                }
            }
        }


        binding.fabFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                viewModel.insert(id, username, avatar, html)
                binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                viewModel.delete(id)
                binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
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
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_HTML = "extra_html"
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.textfollower, R.string.textfollowing)
    }
}