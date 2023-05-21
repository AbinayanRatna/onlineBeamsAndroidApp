package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinebeamsandroidapp.databinding.FragmentAddBinding
import com.example.onlinebeamsandroidapp.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        val output1 = arguments?.getString("message").toString()
        val output2 = arguments?.getString("message2").toString()


        return binding.root
    }


}