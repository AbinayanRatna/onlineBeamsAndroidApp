package com.example.onlinebeamsandroidapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.adminFragments.AddFragment
import com.example.onlinebeamsandroidapp.adminFragments.CategoryFragment
import com.example.onlinebeamsandroidapp.adminFragments.DescriptionFragment
import com.example.onlinebeamsandroidapp.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivityAdmin : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding
    private val addFragment = AddFragment()
    private val categoryFragment = CategoryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
        replaceFragment(CategoryFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.add -> {
                    replaceFragment(DescriptionFragment())
                }

                R.id.home -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, CategoryFragment())
                    transaction.commit()
                }

                R.id.logOut -> {
                    val intent = Intent(this, logInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null).commit()
        }
    }

    override fun toastMake(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun sendWhatsappMessage(message: String) {
        val number: String = "+94775713207"
        val packageManager: PackageManager = packageManager
        val i = Intent(Intent.ACTION_VIEW)
        val url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(
            message,
            "UTF-8"
        )
        i.setPackage("com.whatsapp")
        i.data = Uri.parse(url)


        if (i.resolveActivity(packageManager) != null) {
            startActivity(i)
        } else {
            Toast.makeText(this, "Download the whatsapp app and then try again", Toast.LENGTH_SHORT)
                .show()
        }
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
        transaction.replace(R.id.fragment_container, fragment).addToBackStack(null).commit()

    }


}