package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.anak.TabListAnakFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.kesehatan.TabListKesehatanFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.nifas.TabListNifasFragment

class LaporanSectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TabListKesehatanFragment()
            1 -> fragment = TabListNifasFragment()
            2 -> fragment = TabListAnakFragment()
        }
        return fragment as Fragment
    }
}