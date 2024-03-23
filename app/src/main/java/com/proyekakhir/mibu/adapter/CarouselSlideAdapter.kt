package com.proyekakhir.mibu.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.proyekakhir.mibu.ui.app.custom_view.CarouselSlideFragment

class CarouselSlideAdapter(fragment: Fragment, private val imageList: List<Int>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment {
        return CarouselSlideFragment(imageList[position])
    }
}
