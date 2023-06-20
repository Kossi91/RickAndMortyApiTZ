package com.example.rickandmortyapitz.presentation.ui.fragments.character

import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.constants.Constants.CHARACTER_GENDER_QUERY
import com.example.data.constants.Constants.CHARACTER_SPECIES_QUERY
import com.example.data.constants.Constants.CHARACTER_STATUS_QUERY
import com.example.rickandmortyapitz.R
import com.example.rickandmortyapitz.databinding.FragmentCharactersFilterBinding
import com.example.rickandmortyapitz.presentation.base.BaseDialogFragment
import com.example.rickandmortyapitz.presentation.extensions.setOnCheckedChangeListenerAndRetrieveItsText
import com.example.rickandmortyapitz.presentation.models.CharacterFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFilterFragment :
    BaseDialogFragment<FragmentCharactersFilterBinding>(R.layout.fragment_characters_filter) {

    override val binding by viewBinding(FragmentCharactersFilterBinding::bind)
    private val characterFilter = CharacterFilter()

    override fun setupListeners() {
        listenToStatusChanges()
        listenToSpeciesChanges()
        listenToGenderChanges()
        resetFilters()
        applyFilters()
    }

    private fun listenToStatusChanges() {
        listenToRadioGroupCheckedStatusAndRetrieveText(
            CHARACTER_STATUS_QUERY,
            binding.btmRdAlive,
            binding.btmRdDead,
            binding.btmRdUnknown,
        )
    }

    private fun listenToSpeciesChanges() {
        listenToRadioGroupCheckedStatusAndRetrieveText(
            CHARACTER_SPECIES_QUERY,
            binding.btmRdHuman,
            binding.btmRdHumanoid,
            binding.btmRdAlien,
        )
    }

    private fun listenToGenderChanges() {
        listenToRadioGroupCheckedStatusAndRetrieveText(
            CHARACTER_GENDER_QUERY,
            binding.btmRdMale,
            binding.btmRdFemale,
            binding.btmGenderUnknown,
        )
    }

    private fun resetFilters() = with(binding) {
        btnReset.setOnClickListener {
            radioGroupStatus.clearCheck()
            radioGroupSpecies.clearCheck()
            radioGroupGender.clearCheck()
            characterFilter.status = null
            characterFilter.species = null
            characterFilter.gender = null
        }
    }

    private fun applyFilters() = with(binding) {
        btnApply.setOnClickListener {
            val filterStatus = characterFilter.status
            val filterGender = characterFilter.gender
            val filterSpecies = characterFilter.species
            findNavController().navigate(
                CharactersFilterFragmentDirections.actionCharactersFilterFragmentToCharacterFragment(
                    filterStatus, filterGender, filterSpecies
                )
            )
        }
    }

    private fun listenToRadioGroupCheckedStatusAndRetrieveText(
        fieldToChange: String,
        vararg radioButtons: RadioButton,
    ) {
        radioButtons.forEach { radio ->
            characterFilter.apply {
                when (fieldToChange) {
                    CHARACTER_STATUS_QUERY -> {
                        radio.isChecked = radio.text.toString() === status
                    }

                    CHARACTER_SPECIES_QUERY -> {
                        radio.isChecked = radio.text.toString() == species
                    }

                    CHARACTER_GENDER_QUERY -> {
                        radio.isChecked = radio.text.toString() == gender
                    }

                    else -> {
                        binding.radioGroupStatus.clearCheck()
                        binding.radioGroupGender.clearCheck()
                        binding.radioGroupSpecies.clearCheck()
                    }
                }
            }
            radio.setOnCheckedChangeListenerAndRetrieveItsText { radioButtonText ->
                when (fieldToChange) {
                    CHARACTER_STATUS_QUERY -> characterFilter.status = radioButtonText

                    CHARACTER_SPECIES_QUERY -> characterFilter.species = radioButtonText

                    CHARACTER_GENDER_QUERY -> characterFilter.gender = radioButtonText
                }
            }
        }
    }
}