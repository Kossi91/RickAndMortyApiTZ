package com.example.rickandmortyapitz.presentation.ui.fragments.episode

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitz.R
import com.example.rickandmortyapitz.databinding.FragmentEpisodeBinding
import com.example.rickandmortyapitz.presentation.base.BaseFragment
import com.example.rickandmortyapitz.presentation.extensions.searchItem
import com.example.rickandmortyapitz.presentation.ui.adapters.EpisodeAdapter
import com.example.rickandmortyapitz.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    private val episodeAdapter: EpisodeAdapter = EpisodeAdapter()
    private val viewModel: EpisodeViewModel by viewModels()

    override fun initialize() {
        setupRecyclerview()
    }

    private fun setupRecyclerview() = with(binding) {
        rvEpisode.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { episodeAdapter.retry() }
            )
        }
    }

    override fun setupRequest() {
        viewModel.fetchEpisodes("", "")
    }

    override fun setupObserves() {
        viewModel.episodeState.collectPaging {
            episodeAdapter.submitData(it)
        }
    }

    override fun setupListener() {
        searchItem()
    }

    private fun searchItem() {
        binding.search.searchItem {
            viewModel.fetchEpisodes(
                it,
                ""
            )
        }
    }
}