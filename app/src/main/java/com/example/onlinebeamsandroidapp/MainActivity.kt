package com.example.onlinebeamsandroidapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.adminFragments.AddFragment
import com.example.onlinebeamsandroidapp.adminFragments.CategoryFragment
import com.example.onlinebeamsandroidapp.adminFragments.DescriptionFragment
import com.example.onlinebeamsandroidapp.adminFragments.EditFragment
import com.example.onlinebeamsandroidapp.adminFragments.ItemsFragment
import com.example.onlinebeamsandroidapp.adminFragments.UserCategoryFragment
import com.example.onlinebeamsandroidapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding
    private val editFragment = EditFragment()
    private val addFragment = AddFragment()
    private val itemsFragment = ItemsFragment()
    private val descriptionFragment = DescriptionFragment()
    private val categoryFragment = CategoryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(UserCategoryFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.add -> {
                    replaceFragment(descriptionFragment)
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

    override fun sendWhatsappMessage(context: Context, imageUrl: String,message:String) {
        GlobalScope.launch(Dispatchers.Main) {
            val file = downloadImage(context, imageUrl)
            if (file != null) {
                shareImage(context, file)
            }
        }
    }

    private suspend fun downloadImage(context: Context, imageUrl: String): File? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val file = File(context.cacheDir, "image.jpg")
                val outputStream = FileOutputStream(file)

                val buffer = ByteArray(4096)
                var bytesRead = inputStream.read(buffer)
                while (bytesRead != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                    bytesRead = inputStream.read(buffer)
                }

                outputStream.flush()
                outputStream.close()
                inputStream.close()

                file
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun shareImage(context: Context, file: File) {
        val authority = "${context.packageName}.provider"
        val fileUri = FileProvider.getUriForFile(context, authority, file)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, fileUri)
        intent.setPackage("com.whatsapp")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        context.startActivity(Intent.createChooser(intent, "Share Image via"))
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