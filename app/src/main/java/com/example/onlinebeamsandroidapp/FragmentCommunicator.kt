package com.example.onlinebeamsandroidapp

import androidx.fragment.app.Fragment

interface FragmentCommunicator {
    fun toastMake(message: String)
    fun sendWhatsappMessage(message: String)
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

