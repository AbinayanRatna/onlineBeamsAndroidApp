package com.example.onlinebeamsandroidapp.adminFragments

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.databinding.FragmentAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var communicator: FragmentCommunicator
    var typeSelectButton: String=""
    var editFragment = EditFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        val output = arguments?.getString("message").toString()
        if (output == "Description Not Available") {
            binding.tvDescriptionAdded.text = getString(R.string.description_not_added)
        } else {
            binding.tvDescriptionAdded.text = getString(R.string.description_added)
        }


        binding.babyCareRdbtn.setOnClickListener {
            typeSelectButton = binding.babyCareRdbtn.text.toString()
        }
        binding.cosmeticsRdbtn.setOnClickListener {
            typeSelectButton = binding.cosmeticsRdbtn.text.toString()
        }
        binding.electronicRdbtn.setOnClickListener {
            typeSelectButton = binding.electronicRdbtn.text.toString()
        }
        binding.otherRdbtn.setOnClickListener {
            typeSelectButton = binding.otherRdbtn.text.toString()
        }
        binding.lightsRdbtn.setOnClickListener {
            typeSelectButton = binding.lightsRdbtn.text.toString()
        }
        binding.powertoolRdbtn.setOnClickListener {
            typeSelectButton = binding.powertoolRdbtn.text.toString()
        }
        binding.kitchenRdbtn.setOnClickListener {
            typeSelectButton = binding.kitchenRdbtn.text.toString()
        }

        binding.cancelBtn.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, CategoryFragment())
            transaction?.commit()
        }

        binding.selectImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        binding.proceedBtn.setOnClickListener {
            when (typeSelectButton) {
                "Electronic" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("Electronic")
                }

                "Power Tool" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("PowerTool")
                }

                "Kitchen Tool" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("KitchenTool")
                }

                "Baby care" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("BabyCare")
                }

                "Cosmetics" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("Cosmetics")
                }

                "Lights" -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("Lights")
                }

                else -> {
                    databaseRef = FirebaseDatabase.getInstance().getReference("Others")
                }
            }
            saveDataToDatabase()
        }
        return binding.root


    }

    var selectedImageUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImageUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            binding.addImage.setBackgroundDrawable(bitmapDrawable)

        }
    }

    fun saveDataToDatabase() {
        val itemName = binding.nameEditText.text.toString()
        val itemWarrenty = binding.warrentyEditText.text.toString()
        val itemPrice = binding.priceEditText.text.toString()
        var imgUrl = ""
        val output = arguments?.getString("message").toString()

        val itemId = databaseRef.push().key
        communicator.toastMake("Wait.Saving Process will take some time.")
        if (selectedImageUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/image/$filename")
        ref.putFile(selectedImageUri!!)
            .addOnSuccessListener { taskSnapshot ->

                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        imgUrl = uri.toString()
                        val product =
                            ItemClass(itemId, itemName, imgUrl, itemWarrenty, output, itemPrice)
                        databaseRef.child(itemId!!).setValue(product).addOnSuccessListener {

                            fragmentManager?.beginTransaction()
                                ?.replace(R.id.fragment_container, CategoryFragment())?.commit()
                            communicator.toastMake("Details saved")

                        }.addOnFailureListener {

                            communicator.toastMake("Failed to save")


                        }
                    }
            }.addOnFailureListener {
                communicator.toastMake("Failed to attach image to database")
            }


    }


}
