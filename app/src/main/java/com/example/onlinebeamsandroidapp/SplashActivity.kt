package com.example.onlinebeamsandroidapp

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.onlinebeamsandroidapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    private val splashTime: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //   val imgSplash: ImageView = findViewById(R.id.img_splash)

    //    val rightToLeftAnim = AnimationUtils.loadAnimation(this, R.anim.right_to_left)
       // imgSplash.startAnimation(rightToLeftAnim)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent=Intent(this,logInActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTime)
    }
}