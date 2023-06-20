package com.example.rickandmortyapitz.presentation.models

import com.example.domain.models.LocationModel
import com.example.rickandmortyapitz.presentation.base.BaseInterfaceCallback

data class LocationModelUI(
    override var id: Int,
    var name: String,
    var type: String,
    val dimension: String,
    val created: String
): BaseInterfaceCallback

fun LocationModel.toLocationUI() = LocationModelUI(id, name, type, dimension, created)