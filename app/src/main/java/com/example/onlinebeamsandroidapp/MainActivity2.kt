package com.example.onlinebeamsandroidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinebeamsandroidapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        /*
             val message:String=binding.edittext.text.toString()
             //val message:String=binding.edittext.text.toString()

             binding.btndone.setOnClickListener {
                 val number:String="+94775713207"
                 val packageManager : PackageManager = packageManager
                 val i = Intent(Intent.ACTION_VIEW)
                 val url = "https://api.whatsapp.com/send?phone=" + number.toString() + "&text="+ URLEncoder.encode(message.toString(),"UTF-8")
                 i.setPackage("com.whatsapp")
                 i.data = Uri.parse(url)
                 if(i.resolveActivity(packageManager) != null){
                     startActivity(i)
                 }
             }



             binding.btndone.setOnClickListener {
                 val packageManager : PackageManager = packageManager
                 val i = Intent(Intent.ACTION_VIEW)
                // val url = "https://api.whatsapp.com/send?phone=" + number.text.toString() + "&text="+ URLEncoder.encode(msg.text.toString(),"UTF-8")
                 val url = "https://wa.me/message/HN3JEBCUUCO4K1" + "&text="+message
                 i.setPackage("com.whatsapp")
                 i.data = Uri.parse(url)
                 if(i.resolveActivity(packageManager) != null){
                     startActivity(i)
             }
         }*/

    }
}