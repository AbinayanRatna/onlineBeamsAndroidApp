package com.example.onlinebeamsandroidapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.onlinebeamsandroidapp.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var file: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        //    val message:String=binding.edittext.text.toString()
        //val message:String=binding.edittext.text.toString()
        /*
                     binding.btndone.setOnClickListener {

                         val packageManager : PackageManager = packageManager
                         val i = Intent(Intent.ACTION_VIEW)
                         val number:String="+94775713207"
                         val url = "https://api.whatsapp.com/send?phone=" + number.toString() + "&text="+ URLEncoder.encode(message.toString(),"UTF-8")
                         i.setPackage("com.whatsapp")
                         i.data = Uri.parse(url)
                         if(i.resolveActivity(packageManager) != null){
                             startActivity(i)
                         }
                     }

        */

        binding.btndone.setOnClickListener {
            sendImageToWhatsApp(
                this,
                "https://firebasestorage.googleapis.com/v0/b/onlinebeamsandroidapp.appspot.com/o/image%2F08f212e2-e762-46d8-a54f-71cb8c7b4da7?alt=media&token=3253fb76-ae9e-420d-bbfd-dd40c1629911"
            )

        }


    }

    fun sendImageToWhatsApp(context: Context, imageUrl: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val file = downloadImage(context, imageUrl)
            if (file != null) {
                shareImage(context, file, "hello")
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

    private fun shareImage(context: Context, file: File, message: String) {
        //val authority = "${context.packageName}.provider"
      //  val fileUri = FileProvider.getUriForFile(context, authority, file)
        val number: String = "+94775713207"

        val authority = "${context.packageName}.provider"
        val fileUri = FileProvider.getUriForFile(context, authority, file)

        val intent = Intent(Intent.ACTION_SEND)
        intent.setDataAndType(fileUri, "image/*")
        intent.putExtra(Intent.EXTRA_STREAM, fileUri)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.setPackage("com.whatsapp")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Update the phoneNumber to send the message to a specific number
        intent.data = Uri.parse("https://api.whatsapp.com/send?phone=+94765778174")

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(intent, "Share Image via"))
        } else {
            // Handle the case when WhatsApp is not installed or no app can handle the intent
            // You can display an error message or take alternative actions
            context.startActivity(Intent.createChooser(intent, "Share Image via"))
            Toast.makeText(this,"Available",Toast.LENGTH_SHORT).show()
        }
        /*

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(intent, "Share Image via"))
        } else {
            Toast.makeText(this,"install whatsapp first",Toast.LENGTH_SHORT).show()
            // You can display an error message or take alternative actions
        }

        */
    }

}