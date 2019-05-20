package com.filip.newsreader.FragmentAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.filip.newsreader.Fragments.*

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    val fragments = arrayOf(
            FirstFragment.newInstance(),
            SecondFragment.newInstance(),
            ThirdFragment.newInstance(),
            FourthFragment.newInstance(),
            FifthFragment.newInstance(),
            SixthFragment.newInstance(),
            SeventhFragment.newInstance(),
            EightFragment.newInstance(),
            NinthFragment.newInstance(),
            TenthFragment.newInstance()
    )

    val titles = arrayOf("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Sevent", "Eight", "Ninth", "Tenth")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size;
    }
}