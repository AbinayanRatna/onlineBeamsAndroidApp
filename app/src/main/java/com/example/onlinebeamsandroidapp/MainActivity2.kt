package com.example.onlinebeamsandroidapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapImageDecoderResourceDecoder
import com.example.onlinebeamsandroidapp.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


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

*/

        binding.btndone.setOnClickListener {
sendImageToWhatsApp(this,"https://firebasestorage.googleapis.com/v0/b/onlinebeamsandroidapp.appspot.com/o/image%2F08f212e2-e762-46d8-a54f-71cb8c7b4da7?alt=media&token=3253fb76-ae9e-420d-bbfd-dd40c1629911")

        }






    }

    fun sendImageToWhatsApp(context: Context, imageUrl: String) {
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

}