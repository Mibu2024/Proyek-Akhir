package com.proyekakhir.mibu.user.ui.kehamilan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.proyekakhir.mibu.user.ui.kehamilan.kesehatan.TabKesehatanFragment
import com.proyekakhir.mibu.user.ui.kehamilan.nifas.TabNifasFragment

class SectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TabKesehatanFragment()
            1 -> fragment = TabNifasFragment()
        }
        return fragment as Fragment
    }
}