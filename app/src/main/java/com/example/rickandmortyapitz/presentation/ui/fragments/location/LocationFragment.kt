package com.example.rickandmortyapitz.presentation.ui.fragments.location

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitz.R
import com.example.rickandmortyapitz.databinding.FragmentLocationBinding
import com.example.rickandmortyapitz.presentation.base.BaseFragment
import com.example.rickandmortyapitz.presentation.extensions.bindUIToLoadState
import com.example.rickandmortyapitz.presentation.extensions.searchItem
import com.example.rickandmortyapitz.presentation.ui.activity.MainActivity
import com.example.rickandmortyapitz.presentation.ui.adapters.LocationAdapter
import com.example.rickandmortyapitz.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter: LocationAdapter = LocationAdapter()

    override fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        rvLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { locationAdapter.retry() }
            )
        }
    }

    override fun setupRequest() {
        viewModel.fetchLocations("", "", "")
    }

    override fun setupObserves() {
        viewModel.locationState.collectPaging {
            locationAdapter.submitData(it)
        }
    }

    override fun setupListener() {
        searchItem()
    }

    private fun searchItem() {
        binding.search.searchItem {
            viewModel.fetchLocations(
                it,
                "",
                ""
            )
        }
    }
}