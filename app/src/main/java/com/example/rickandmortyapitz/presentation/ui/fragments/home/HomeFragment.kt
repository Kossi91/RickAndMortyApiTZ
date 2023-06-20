package com.example.rickandmortyapitz.presentation.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitz.R
import com.example.rickandmortyapitz.databinding.FragmentHomeBinding
import com.example.rickandmortyapitz.presentation.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        binding.viewPager.adapter = PagerAdapter(this@HomeFragment)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Characters"
                }

                1 -> {
                    tab.text = "Episodes"
                }

                2 -> {
                    tab.text = "Locations"
                }
            }
        }.attach()
    }
}