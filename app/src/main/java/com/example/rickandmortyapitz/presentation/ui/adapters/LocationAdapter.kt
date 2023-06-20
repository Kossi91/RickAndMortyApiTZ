package com.example.rickandmortyapitz.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapitz.databinding.ItemLocationBinding
import com.example.rickandmortyapitz.presentation.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitz.presentation.models.LocationModelUI

class LocationAdapter(
) : PagingDataAdapter<LocationModelUI, LocationAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(it: LocationModelUI) = with(binding) {
            tvNameLocation.text = it.name
            tvTypeLocation.text = it.type
            tvDimension.text = it.dimension
            tvCreatedLocation.text = it.created
        }
    }
}