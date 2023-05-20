package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentViewBinding


class ViewFragment : Fragment() {
    private lateinit var binding:FragmentViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentViewBinding.inflate(inflater,container,false)
        return binding.root
    }

}