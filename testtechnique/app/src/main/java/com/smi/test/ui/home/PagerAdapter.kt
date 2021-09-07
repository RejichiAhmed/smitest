package com.smi.test.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager,
                   private val pages: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence {
        if(position == 0) return "Marques Premium"
        if(position == 1) return "Toutes les marqures"
        return return "OBJECT ${(position + 1)}"
    }
}