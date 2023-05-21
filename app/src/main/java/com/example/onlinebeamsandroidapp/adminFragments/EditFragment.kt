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
        val output3 = arguments?.getString("message3").toString()
        val output4 = arguments?.getString("message4").toString()
        val output5 = arguments?.getString("message5").toString()
        val output6 = arguments?.getString("message6").toString()

        binding.nameEditText.setText(output4)
        binding.warrentyEditText.setText(output5)
        binding.priceEditText.setText(output6)

        return binding.root
    }


}