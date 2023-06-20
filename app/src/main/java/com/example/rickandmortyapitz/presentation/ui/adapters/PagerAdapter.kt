package com.example.rickandmortyapitz.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickandmortyapitz.presentation.ui.fragments.character.CharacterFragment
import com.example.rickandmortyapitz.presentation.ui.fragments.episode.EpisodeFragment
import com.example.rickandmortyapitz.presentation.ui.fragments.location.LocationFragment

class PagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()
            1 -> EpisodeFragment()
            else -> LocationFragment()
        }
    }
}

//class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
//
//    override fun getItemCount(): Int = 3
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> CharacterFragment()
//            1 -> EpisodeFragment()
//            else -> LocationFragment()
//        }
//    }
//}
