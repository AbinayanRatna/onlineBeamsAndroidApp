package com.example.onlinebeamsandroidapp

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment

interface FragmentCommunicator {
    fun toastMake(message: String)
    fun sendWhatsappMessage(context: Context, imageUrl: String,message: String)
    fun passData(
        editTextData: String?,
        editTextData2: String?,
        editTextData3: String?,
        editTextData4: String?,
        editTextData5: String?,
        editTextData6: String?,
        editTextData7: String?,
        fragment: Fragment
    )
}

