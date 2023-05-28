package com.example.onlinebeamsandroidapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinebeamsandroidapp.databinding.ActivityLoginBinding


class logInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnGuest.setOnClickListener {
            val intent = Intent(this, MainActivityUser::class.java)
            startActivity(intent)
        }

    }


}