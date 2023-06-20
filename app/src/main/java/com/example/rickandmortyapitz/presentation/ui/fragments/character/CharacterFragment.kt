package com.example.rickandmortyapitz.presentation.ui.fragments.character

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitz.R
import com.example.rickandmortyapitz.databinding.FragmentCharacterBinding
import com.example.rickandmortyapitz.presentation.base.BaseFragment
import com.example.rickandmortyapitz.presentation.extensions.navigateSafely
import com.example.rickandmortyapitz.presentation.extensions.searchItem
import com.example.rickandmortyapitz.presentation.ui.adapters.CharacterAdapter
import com.example.rickandmortyapitz.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()
    private val args by navArgs<CharacterFragmentArgs>()

    override fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        rvCharacter.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                characterAdapter.withLoadStateFooter(footer = LoadStateAdapter { characterAdapter.retry() })
        }
    }

    override fun setupRequest() {
        if (args.status == "" || args.gender == "" || args.species == "") {
            viewModel.fetchCharacters("", "", "", "")

        } else {
            viewModel.fetchCharactersFilter(
                "", args.status, args.gender, args.species ?: ""
            )
        }
    }

    override fun setupObserves() {
        if (args.status == "" || args.gender == "" || args.species == "") {
            viewModel.characterState.collectPaging {
                characterAdapter.submitData(it)
            }
        } else {
            viewModel.characterStateFilter.collectPaging {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun setupListener() {
        searchItems()
        filterListener()
    }

    private fun searchItems() {
        if (args.status == "" || args.gender == "" || args.species == "") {
            binding.search.searchItem {
                viewModel.fetchCharacters(
                    it, "", "", ""
                )
            }
        } else {
            binding.search.searchItem {
                viewModel.fetchCharactersFilter(
                    it, args.status, args.gender, args.species.toString()
                )
            }
        }
    }

    private fun filterListener() {
        binding.btmFilter.setOnClickListener {
            findNavController().navigateSafely(
                CharacterFragmentDirections.actionCharacterFragmentToCharactersFilterFragment()
            )
        }
    }
}