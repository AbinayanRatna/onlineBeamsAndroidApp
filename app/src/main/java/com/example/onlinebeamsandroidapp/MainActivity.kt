package com.example.onlinebeamsandroidapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.adminFragments.AddFragment
import com.example.onlinebeamsandroidapp.adminFragments.CategoryFragment
import com.example.onlinebeamsandroidapp.adminFragments.DescriptionFragment
import com.example.onlinebeamsandroidapp.adminFragments.EditFragment
import com.example.onlinebeamsandroidapp.adminFragments.ItemsFragment
import com.example.onlinebeamsandroidapp.adminFragments.UserCategoryFragment
import com.example.onlinebeamsandroidapp.adminFragments.UserViewFragment
import com.example.onlinebeamsandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding
    private val editFragment = EditFragment()
    private val addFragment = AddFragment()
    private val itemsFragment = ItemsFragment()
    private val descriptonFragment = DescriptionFragment()
    private val categoryFragment = CategoryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(UserViewFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.add -> {
                    replaceFragment(descriptonFragment)
                }

                R.id.home -> {
                    replaceFragment(categoryFragment)
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

    override fun toastMake(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun passData(
        editTextData: String?,
        editTextData2: String?,
        editTextData3: String?,
        editTextData4: String?,
        editTextData5: String?,
        editTextData6: String?,
        editTextData7: String?,
        fragment: Fragment
    ) {
        val empty: String = "Description Not Available"
        val bundle = Bundle()
        if (editTextData == "") {
            bundle.putString("message", empty)
        } else {
            bundle.putString("message", editTextData)
        }
        bundle.putString("message2", editTextData2)
        bundle.putString("message3", editTextData3)
        bundle.putString("message4", editTextData4)
        bundle.putString("message5", editTextData5)
        bundle.putString("message6", editTextData6)
        bundle.putString("message7", editTextData7)
        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container, fragment).commit()

    }


}