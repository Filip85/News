package com.filip.tasky

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FrgamentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    val fragments = arrayOf(
            NewCategory.newInstance(),
            NewTask.newInstance(),
            AllTasks.newInstance()
    )
    val titles = arrayOf("New category", "New task", "Your Tasks")

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}