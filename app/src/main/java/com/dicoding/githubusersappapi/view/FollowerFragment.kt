package com.dicoding.githubusersappapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dicoding.githubusersappapi.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowerBinding.bind(view)
    }
}