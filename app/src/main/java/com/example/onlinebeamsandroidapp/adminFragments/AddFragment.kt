package com.example.onlinebeamsandroidapp.adminFragments

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    var editFragment=EditFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        val output = arguments?.getString("message").toString()
        if (output == "Description Not Available") {
            binding.tvDescriptionAdded.text = getString(R.string.description_not_added)
        } else {
            binding.tvDescriptionAdded.text = getString(R.string.description_added)
        }

        binding.cancelBtn.setOnClickListener {
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, editFragment)
            transaction?.commit()
        }

        binding.selectImageBtn.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        return binding.root


    }
    var selectedImageUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImageUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            binding.addImage.setBackgroundDrawable(bitmapDrawable)

        }
    }


}