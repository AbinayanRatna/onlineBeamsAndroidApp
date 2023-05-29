package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.adaptors.UserItemAdaptor
import com.example.onlinebeamsandroidapp.databinding.FragmentItemsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale
import java.util.Locale.filter
import java.util.Locale.getDefault

class UserItemFragment : Fragment() {
    private lateinit var binding: FragmentItemsBinding
    private lateinit var communicator: FragmentCommunicator
    private lateinit var databaseRef: DatabaseReference
     private lateinit var itemArrayList: ArrayList<ItemClass>
     private lateinit var itemSearchList: ArrayList<ItemClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)
        itemArrayList = arrayListOf<ItemClass>()
        itemSearchList = arrayListOf<ItemClass>()
        getUserData()
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemSearchList.clear()
                var searchText=newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    itemArrayList.forEach {
                        if(it.item_Name!!.lowercase(Locale.getDefault()).contains(searchText)){
                            Log.d("done1","done1")
                            itemSearchList.add(it)
                        }
                    }
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    itemSearchList.clear()
                    itemSearchList.addAll(itemArrayList)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return binding.root
    }


    private fun getUserData() {

        val output = arguments?.getString("message").toString()
        var category: String = ""
        when (output) {
            "Electronics" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("Electronic")
                category = "Electronic"
            }

            "Power Tools" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("PowerTool")
                category = "PowerTool"
            }

            "Kitchen tools & utensils" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("KitchenTool")
                category = "KitchenTool"
            }

            "Baby care & toys" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("BabyCare")
                category = "BabyCare"
            }

            "Cosmetics" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("Cosmetics")
                category = "Cosmetics"
            }

            "Lights" -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("Lights")
                category = "Lights"
            }

            else -> {
                databaseRef = FirebaseDatabase.getInstance().getReference("Others")
                category = "Others"
            }
        }


        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {

                        val item = userSnapshot.getValue(ItemClass::class.java)
                        itemArrayList.add(item!!)

                    }
                    itemSearchList.addAll(itemArrayList)
                    val mAdapter = UserItemAdaptor(itemSearchList, this@UserItemFragment)
                    binding.recyclerView.adapter = mAdapter
                    binding.progressBar.visibility = View.GONE
                    mAdapter.setOnItemClickListener(object : UserItemAdaptor.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            communicator.passData(
                                itemArrayList[position].item_Id,
                                category,
                                itemArrayList[position].item_Descrip,
                                itemArrayList[position].item_Name,
                                itemArrayList[position].item_Warrenty,
                                itemArrayList[position].item_Price,
                                itemArrayList[position].item_Image,
                                UserViewFragment()
                            )
                        }

                    })


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


}

