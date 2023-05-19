package com.example.onlinebeamsandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.databinding.ActivityMainBinding
import com.example.onlinebeamsandroidapp.fragments.AddFragment
import com.example.onlinebeamsandroidapp.fragments.CategoryFragment
import com.example.onlinebeamsandroidapp.fragments.HomeFragment
import com.example.onlinebeamsandroidapp.fragments.ItemsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val addFragment = AddFragment()
    private val itemsFragment = ItemsFragment()
    private val categoryFragment = CategoryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.add->{replaceFragment(addFragment)}
                R.id.home->{replaceFragment(homeFragment)}
                R.id.logOut->{replaceFragment(categoryFragment)}
            }
            true
        }



    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}