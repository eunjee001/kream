package com.kkyoungs.kream

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlidingImagesAdapter (activity: FragmentActivity, private val itemsCount :Int) :FragmentStateAdapter(activity) {
    override fun getItemCount() = itemsCount

    override fun createFragment(position: Int) = RecommendFragment.getInstance(position)
}