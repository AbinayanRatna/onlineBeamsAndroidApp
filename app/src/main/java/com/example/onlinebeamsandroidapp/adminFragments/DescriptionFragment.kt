package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentDescriptionBinding


class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var communicator: FragmentCommunicator
    var addFragment = AddFragment()
    var editFragment = EditFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.descriptionNextBtn.setOnClickListener {
            communicator.passData(binding.etDescription.text.toString(),"","","","","", addFragment)
            Toast.makeText(activity, "Description Added", Toast.LENGTH_SHORT).show()
        }

        binding.descriptionCancelBtn.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, editFragment)
            transaction?.commit()
        }
        return binding.root
    }

}