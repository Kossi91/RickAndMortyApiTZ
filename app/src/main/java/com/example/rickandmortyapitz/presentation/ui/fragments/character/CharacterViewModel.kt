package com.example.rickandmortyapitz.presentation.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.data.repositories.CharacterRepositoryImpl
import com.example.rickandmortyapitz.presentation.base.BaseViewModel
import com.example.rickandmortyapitz.presentation.models.CharacterModelUI
import com.example.rickandmortyapitz.presentation.models.toCharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl,
) : BaseViewModel() {

    private val _charactersState = mutableStateWithPagingFlow<CharacterModelUI>()
    val characterState = _charactersState.asStateFlow()

    fun fetchCharacters(name: String?, status: String?, gender: String?, species: String?) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender, species).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _charactersState.value = it.map { it.toCharacterUI() }
                }
        }

    private val _characterStateFilter = mutableStateWithPagingFlow<CharacterModelUI>()
    val characterStateFilter = _characterStateFilter.asStateFlow()

    fun fetchCharactersFilter(name: String?, status: String?, gender: String?, species: String) =
        viewModelScope.launch {
            repository.fetchCharacter(name, status, gender, species).cachedIn(viewModelScope)
                .collectLatest { it ->
                    _characterStateFilter.value = it.map { it.toCharacterUI() }
                }
        }
}