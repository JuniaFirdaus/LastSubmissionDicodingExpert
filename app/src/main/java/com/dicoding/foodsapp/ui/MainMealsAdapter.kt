package com.dicoding.foodsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.foodsapp.home.HomeFragment

class MainMealsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val className: String
        get() = "com.dicoding.favorite.FavoriteFragment"

    private fun instantiateFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            null
        }
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = instantiateFragment(className)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}