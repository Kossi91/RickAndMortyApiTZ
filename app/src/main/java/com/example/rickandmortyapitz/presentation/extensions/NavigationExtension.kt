package com.example.rickandmortyapitz.presentation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.directionsSafeNavigation(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}