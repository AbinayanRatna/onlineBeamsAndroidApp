package com.example.onlinebeamsandroidapp.adminFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.adaptors.ItemAdaptors
import com.example.onlinebeamsandroidapp.databinding.FragmentItemsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ItemsFragment : Fragment() {

    private lateinit var binding:FragmentItemsBinding
    private lateinit var databaseRef:DatabaseReference
    private lateinit var itemArrayList:ArrayList<ItemClass>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentItemsBinding.inflate(inflater,container,false)
        binding.recyclerView.layoutManager=LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)
        itemArrayList= arrayListOf<ItemClass>()
        getUserData()
        return binding.root
    }

    private fun getUserData(){
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
                databaseRef= FirebaseDatabase.getInstance().getReference("BabyCare")
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

        Log.i("Firebase image URL", "date8")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("Firebase image URL", "date8")
                if (snapshot.exists()) {
                    Log.i("Firebase image URL", "date8")
                    for (userSnapshot in snapshot.children) {

                        val item = userSnapshot.getValue(ItemClass::class.java)
                        itemArrayList.add(item!!)

                    }
                    val mAdapter = ItemAdaptors(itemArrayList, activity!!)
                    binding.recyclerView.adapter = mAdapter
/*
                    mAdapter.setOnItemClickListener(object : itemAdaptor.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@AdminViewItems, AdminEditData::class.java)


                            intent.putExtra("name", empList[position].nameOfProduct)
                            intent.putExtra("category", category)
                            intent.putExtra("id", empList[position].id)
                            intent.putExtra("priceOfProduct", empList[position].priceOfProduct)
                            intent.putExtra(
                                "districtOfProduct",
                                empList[position].districtOfProduct
                            )
                            intent.putExtra("image", empList[position].image)

                            startActivity(intent)


                        }

                    })

*/
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}