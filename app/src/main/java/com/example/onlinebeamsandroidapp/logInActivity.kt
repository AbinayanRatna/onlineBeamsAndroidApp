package com.example.onlinebeamsandroidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinebeamsandroidapp.databinding.ActivityLoginBinding
import java.io.File


class logInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var file: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}