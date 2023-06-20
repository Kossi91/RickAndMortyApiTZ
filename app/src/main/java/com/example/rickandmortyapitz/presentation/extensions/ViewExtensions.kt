package com.example.rickandmortyapitz.presentation.extensions

import android.widget.SearchView

fun SearchView.searchItem(method: (name: String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            method(p0)
            return false
        }

    })
}