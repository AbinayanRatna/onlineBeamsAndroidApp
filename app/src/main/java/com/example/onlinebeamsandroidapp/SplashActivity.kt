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
import android.view.ContextThemeWrapper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.onlinebeamsandroidapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    private val splashTime: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //   val imgSplash: ImageView = findViewById(R.id.img_splash)

    //    val rightToLeftAnim = AnimationUtils.loadAnimation(this, R.anim.right_to_left)
       // imgSplash.startAnimation(rightToLeftAnim)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            if(isInternetAvailable(this)){
                val intent=Intent(this,logInActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val builderIntConnect =
                    AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                        .setTitle("No Internet Connection")
                        .setMessage("Turn on the internet and try again")
                        .setPositiveButton("ok") { dialog: DialogInterface, which: Int -> finish() }
                val dialog1 = builderIntConnect.create()
                dialog1.setCancelable(false)
                dialog1.setCanceledOnTouchOutside(false)
                dialog1.show()
                dialog1.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            }


        }, splashTime)
    }
}