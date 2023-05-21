package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentDescriptionBinding
import com.example.onlinebeamsandroidapp.databinding.FragmentDescriptionEditBinding


class DescriptionEditFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        binding.tvDescriptionHeading.text="Edit Description"
        binding.descriptionNextBtn.text="Update"
        binding.descriptionCancelBtn.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container,CategoryFragment())
        }
        val output1 = arguments?.getString("message").toString()
        val output2 = arguments?.getString("message2").toString()
        val output3 = arguments?.getString("message3").toString()
        val output4 = arguments?.getString("message4").toString()
        val output5 = arguments?.getString("message5").toString()
        val output6 = arguments?.getString("message6").toString()

        binding.etDescription.setText(output3)
        return binding.root
    }

}