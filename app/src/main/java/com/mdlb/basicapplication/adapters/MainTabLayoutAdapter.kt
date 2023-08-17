package com.mdlb.basicapplication.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mdlb.basicapplication.fragments.MetricsFragment
import com.mdlb.basicapplication.fragments.ProfilesFragment
import com.mdlb.basicapplication.fragments.RegistersFragment

class MainTabLayoutAdapter (
    private val myContext: Context,
    fm: FragmentManager,
    private var totalTabs: Int
) : FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RegistersFragment()
            }

            1 -> {
                MetricsFragment()
            }

            2 -> {
                ProfilesFragment()
            }

            else -> RegistersFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}