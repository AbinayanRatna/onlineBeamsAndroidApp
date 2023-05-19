package com.example.onlinebeamsandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.databinding.ActivityMainBinding
import com.example.onlinebeamsandroidapp.adminFragments.AddFragment
import com.example.onlinebeamsandroidapp.adminFragments.CategoryFragment
import com.example.onlinebeamsandroidapp.adminFragments.DescriptionFragment
import com.example.onlinebeamsandroidapp.adminFragments.HomeFragment
import com.example.onlinebeamsandroidapp.adminFragments.ItemsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val addFragment = AddFragment()
    private val itemsFragment = ItemsFragment()
    private val descriptonFragment = DescriptionFragment()
    private val categoryFragment = CategoryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.add -> {
                    replaceFragment(descriptonFragment)
                }

                R.id.home -> {
                    replaceFragment(homeFragment)
                }

                R.id.logOut -> {
                    replaceFragment(categoryFragment)
                }
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

    override fun passData(editTextData: String?,fragment: Fragment) {
        val empty: String = "Description Not Available"
        val bundle = Bundle()
        if (editTextData == "") {
            bundle.putString("message", empty)
        } else {
            bundle.putString("message", editTextData)
        }
        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container, fragment).commit()

    }
}