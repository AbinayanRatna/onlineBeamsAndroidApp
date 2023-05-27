package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentUserViewBinding


class UserViewFragment : Fragment() {
    private lateinit var binding: FragmentUserViewBinding
    private lateinit var communicator: FragmentCommunicator
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserViewBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.itemImageShow.setBackgroundResource(0)

        val output1 = arguments?.getString("message")
        val output2 = arguments?.getString("message2")
        val output3 = arguments?.getString("message3")
        val output4 = arguments?.getString("message4")
        val output5 = arguments?.getString("message5")
        val output6 = arguments?.getString("message6")
        val output7 = arguments?.getString("message7")

        binding.itemNameShow.text = output4
        binding.itemDescriptionShow.text = output3
        binding.itemPriceShow.text = output6
        binding.itemWarrentyShow.text = output5
        Glide.with(requireActivity()).load(output7).into(binding.itemImageShow)
        binding.btnCancel.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, UserCategoryFragment())?.commit()
        }
        binding.btnAddAnother.setOnClickListener {
            count = binding.tvCount.text.toString().toInt()
            count++
            binding.tvCount.text = count.toString()
        }
        binding.btnReduceAnother.setOnClickListener {
            count = binding.tvCount.text.toString().toInt()
            count--

            if (count < 0) {
                count = 0
            }
            binding.tvCount.text = count.toString()
        }
        binding.btnbuyNow.setOnClickListener {
            count = binding.tvCount.text.toString().toInt()
            communicator.sendWhatsappMessage("Product name : ${output4!!} ,\n\n  count :  $count \n\n product image: \n${output7!!} \n\n I saw this on the official app. And I am interested.\n")
        }


        return binding.root
    }

}