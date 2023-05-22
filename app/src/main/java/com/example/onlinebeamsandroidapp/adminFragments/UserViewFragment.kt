package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.databinding.FragmentViewBinding


class UserViewFragment : Fragment() {
    private lateinit var binding: FragmentViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

}