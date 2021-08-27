package com.dicoding.foodsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.foodsapp.databinding.ActivityMainBinding
import com.dicoding.foodsapp.ui.MainMealsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var sectionsPagerAdapter: MainMealsAdapter
    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = MainMealsAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.viewPager.isUserInputEnabled = false
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        Toast.makeText(this@MainActivity, "Power Connected", Toast.LENGTH_LONG)
                            .show()
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        Toast.makeText(this@MainActivity, "Power Disconnected", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.menu_home,
            R.string.menu_favorite
        )
    }
}

