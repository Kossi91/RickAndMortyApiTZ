package com.example.rickandmortyapitz.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapitz.databinding.ItemEpisodeBinding
import com.example.rickandmortyapitz.presentation.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitz.presentation.models.EpisodeModelUI

class EpisodeAdapter(
) : PagingDataAdapter<EpisodeModelUI, EpisodeAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(it: EpisodeModelUI) = with(binding) {
            tvNameEpisode.text = it.name
            tvEpisode.text = it.episode
            tvCreatedEpisode.text = it.created
            tvAirDate.text = it.air_date
        }
    }
}