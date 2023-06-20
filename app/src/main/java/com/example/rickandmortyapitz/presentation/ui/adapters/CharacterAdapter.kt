package com.example.rickandmortyapitz.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapitz.databinding.ItemCharacterBinding
import com.example.rickandmortyapitz.presentation.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitz.presentation.extensions.loadImagesWithGlide
import com.example.rickandmortyapitz.presentation.models.CharacterModelUI

class CharacterAdapter :
    PagingDataAdapter<CharacterModelUI, CharacterAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(it: CharacterModelUI?) = with(binding) {
            it?.let {
                itemCharacterImage.loadImagesWithGlide(it.image)
                itemCharacterName.text = it.name
                itemCharacterSpecies.text = it.species
                itemCharacterStatus.text = it.status
                itemCharacterGender.text = it.gender
            }
        }
    }
}