package com.example.onlinebeamsandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinebeamsandroidapp.databinding.ActivityAdminLoginBinding

class AdminLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
        binding.btnLogin.setOnClickListener {
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            when (userName + " " + password) {
                "admin password2" -> {
                    val intent = Intent(this, MainActivityAdmin::class.java)
                    startActivity(intent)
                    finish()
                }

                else -> {
                    Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT).show()
                    binding.userName.text = null
                    binding.password.text = null
                }
            }
        }

        binding.btnGuest.setOnClickListener {
            val intent = Intent(this, MainActivityUser::class.java)
            startActivity(intent)
            finish()
        }

    }
}