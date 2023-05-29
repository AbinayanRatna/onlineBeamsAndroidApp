package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinebeamsandroidapp.CategoryClassOB
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.adaptors.CategoryAdaptor
import com.example.onlinebeamsandroidapp.databinding.FragmentCategoryBinding
import com.example.onlinebeamsandroidapp.isInternetAvailable

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var communicator: FragmentCommunicator
    private lateinit var categoryArrayList: ArrayList<CategoryClassOB>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.setHasFixedSize(true)
        categoryArrayList = arrayListOf<CategoryClassOB>()
        categoryArrayList.add(CategoryClassOB("Electronics", R.drawable.electronic))
        categoryArrayList.add(CategoryClassOB("Power Tools", R.drawable.powertool))
        categoryArrayList.add(CategoryClassOB("Kitchen tools & utensils", R.drawable.kitchen))
        categoryArrayList.add(CategoryClassOB("Baby care & toys", R.drawable.babycare))
        categoryArrayList.add(CategoryClassOB("Cosmetics", R.drawable.cosmetics))
        categoryArrayList.add(CategoryClassOB("Lights", R.drawable.light))
        categoryArrayList.add(CategoryClassOB("Others", R.drawable.other))

        val addAdaptor = CategoryAdaptor(categoryArrayList)
        binding.recyclerView.adapter = addAdaptor
        addAdaptor.setOnCategoryClickListener(
            object : CategoryAdaptor.onCategoryClickListener {
                override fun onItemClick(position: Int) {
                    if(isInternetAvailable(requireContext())){
                        communicator.passData(
                            categoryArrayList[position].category_Name,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ItemsFragment()
                        )
                    }else{
                        communicator.toastMake("No internet connection. Try again!")
                    }

                }

            }
        )
        return binding.root
    }


}