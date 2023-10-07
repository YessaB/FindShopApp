package com.example.findshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.example.findshop.fragments.AddFragment
import com.example.findshop.fragments.HomeFragment
import com.example.findshop.fragments.SettingsFragment
import com.example.findshop.HomeActivity

private lateinit var bottomNavigationView: BottomNavigationView
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val homeFragment=HomeFragment()
        val addFragment = AddFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            // Handle item selection here
            when (item.itemId) {
                R.id.ic_home ->makeCurrentFragment(homeFragment)
                R.id.ic_add ->makeCurrentFragment(addFragment)
                R.id.ic_settings ->makeCurrentFragment(settingsFragment)




            }
            true
        }
    }


    private fun makeCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}