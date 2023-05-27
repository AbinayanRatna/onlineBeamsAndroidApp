package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentDescriptionBinding
import kotlin.concurrent.fixedRateTimer


class DescriptionEditFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var communicator: FragmentCommunicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.tvDescriptionHeading.text = "Edit Description"
        binding.descriptionNextBtn.text = "Update"
        binding.descriptionCancelBtn.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, CategoryFragment())
        }

        binding.descriptionCancelBtn.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container,CategoryFragment())?.commit()
        }
        val output1 = arguments?.getString("message").toString()
        val output2 = arguments?.getString("message2").toString()
        val output3 = arguments?.getString("message3").toString()
        val output4 = arguments?.getString("message4").toString()
        val output5 = arguments?.getString("message5").toString()
        val output6 = arguments?.getString("message6").toString()
        val output7 = arguments?.getString("message7").toString()

        binding.etDescription.setText(output3)

        binding.descriptionNextBtn.setOnClickListener {
            communicator.passData(
                output1,
                output2,
                binding.etDescription.text.toString(),
                output4,
                output5,
                output6,
                output7,
                EditFragment()
            )
        }
        return binding.root
    }

}