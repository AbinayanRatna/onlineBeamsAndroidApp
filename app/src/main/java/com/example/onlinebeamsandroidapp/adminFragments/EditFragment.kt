package com.example.onlinebeamsandroidapp.adminFragments

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentEditBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var communicator: FragmentCommunicator
    private lateinit var databaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        val output4 = arguments?.getString("message4").toString()
        val output5 = arguments?.getString("message5").toString()
        val output6 = arguments?.getString("message6").toString()
        val output7 = arguments?.getString("message7").toString()

        binding.nameEditText.setText(output4)
        binding.warrentyEditText.setText(output5)
        binding.priceEditText.setText(output6)
        Glide.with(requireActivity()).load(output7).into(binding.addImage)

        communicator = activity as FragmentCommunicator
        val itemId = arguments?.getString("message").toString()
        val itemDescription = arguments?.getString("message3").toString()
        val itemName = binding.nameEditText.text.toString()
        val itemWarrenty = binding.warrentyEditText.text.toString()
        val itemPrice = binding.priceEditText.text.toString()
        val itemImage = arguments?.getString("message7").toString()
        binding.updateBtn.setOnClickListener {
            dataAdd(itemId,itemDescription,itemName,itemWarrenty,itemPrice,itemImage)
        }
        binding.deleteBtn.setOnClickListener {
            deleteData()
        }
        binding.selectImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        return binding.root
    }

    var selectedImageUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            binding.addImage.setImageResource(0)
            selectedImageUri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImageUri)
            val bitmapDrawable = BitmapDrawable(bitmap)

            binding.addImage.setBackgroundDrawable(bitmapDrawable)

        }
    }
    private fun dataAdd(itemId:String,itemDescription: String,itemName: String,itemWarrenty: String,itemPrice: String,itemImage: String) {


        if (itemId != null) {
            communicator.toastMake("Wait saving process will take some time")
            if (selectedImageUri != null) {
                val filename = UUID.randomUUID().toString()
                val ref = FirebaseStorage.getInstance().getReference("/image/$filename")
                ref.putFile(selectedImageUri!!)
                    .addOnSuccessListener { taskSnapshot ->
                        taskSnapshot.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener { uri ->
                                val image = uri.toString()
                                updateData(
                                    itemId,
                                    itemName,
                                    itemDescription,
                                    itemWarrenty,
                                    itemPrice,
                                    image
                                )
                                fragmentManager?.beginTransaction()
                                    ?.replace(R.id.fragment_container, CategoryFragment())?.commit()
                                communicator.toastMake("Successfully Updated")

                            }
                    }
            } else {

                val image = itemImage
                communicator.toastMake("Photo saved")
                updateData(itemId, itemName, itemDescription, itemWarrenty, itemPrice, image)
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, CategoryFragment())?.commit()
                communicator.toastMake("Successfully Updated")
            }
        }
    }

    private fun updateData(
        itemId: String,
        itemName: String,
        itemDescription: String,
        itemWarrenty: String,
        itemPrice: String,
        itemImage: String
    ) {
        val itemCategory = arguments?.getString("message2").toString()
        databaseRef = FirebaseDatabase.getInstance().getReference(itemCategory)
        val user = mapOf<String, String>(

            "item_Id" to itemId,
            "item_Name" to itemName,
            "item_Image" to itemImage,
            "item_Warrenty" to itemWarrenty,
            "item_Descrip" to itemDescription,
            "item_Price" to itemPrice
        )
        databaseRef.child(itemId).updateChildren(user).addOnSuccessListener {

        }.addOnFailureListener {

            communicator.toastMake("Failed to Update")

        }


    }

    private fun deleteData() {
        val categoryText = arguments?.getString("message2").toString()
        val itemId = arguments?.getString("message").toString()
        databaseRef = FirebaseDatabase.getInstance().getReference(categoryText!!)
        databaseRef.child(itemId).get().addOnSuccessListener {
            databaseRef.child(itemId).removeValue().addOnSuccessListener {

                communicator.toastMake("Successfully deleted")

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, CategoryFragment())?.commit()


            }.addOnFailureListener {
                communicator.toastMake("Errors in deleting the data.")
            }

        }
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, CategoryFragment())
            ?.commit()
    }

}