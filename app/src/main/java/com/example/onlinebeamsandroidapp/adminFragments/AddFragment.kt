package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentAddBinding
import com.example.onlinebeamsandroidapp.databinding.FragmentDescriptionBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    var homeFragment=HomeFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        var output = arguments?.getString("message").toString()
        if (output == "Description Not Available") {
            binding.tvDescriptionAdded.text = "Description not added"
        } else {
            binding.tvDescriptionAdded.text = "Description added"
        }

        binding.cancelBtn.setOnClickListener {
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, homeFragment)
            transaction?.commit()
        }
        return binding.root


    }


}