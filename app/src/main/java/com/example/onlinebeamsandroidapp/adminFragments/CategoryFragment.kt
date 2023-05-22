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
        categoryArrayList.add(CategoryClassOB("Electronics", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Power Tools", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Kitchen tools & utensils", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Baby care & toys", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Cosmetics", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Lights", R.drawable.baseline_home))
        categoryArrayList.add(CategoryClassOB("Others", R.drawable.baseline_home))

        val addAdaptor = CategoryAdaptor(categoryArrayList)
        binding.recyclerView.adapter = addAdaptor
        addAdaptor.setOnCategoryClickListener(
            object : CategoryAdaptor.onCategoryClickListener {
                override fun onItemClick(position: Int) {
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
                }

            }
        )
        return binding.root
    }


}