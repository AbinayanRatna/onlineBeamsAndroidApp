package com.example.onlinebeamsandroidapp

import androidx.fragment.app.Fragment

interface FragmentCommunicator {
    fun passData(editTextData:String?,fragment: Fragment)
}