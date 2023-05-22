package com.example.onlinebeamsandroidapp.adminFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinebeamsandroidapp.CategoryClassOB
import com.example.onlinebeamsandroidapp.FragmentCommunicator
import com.example.onlinebeamsandroidapp.R
import com.example.onlinebeamsandroidapp.adaptors.UserCategoryAdaptor
import com.example.onlinebeamsandroidapp.databinding.FragmentCategoryBinding


class UserCategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryRecycleView: RecyclerView
    private lateinit var categoryList: ArrayList<CategoryClassOB>
    private lateinit var communicator: FragmentCommunicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        communicator = activity as FragmentCommunicator
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.setHasFixedSize(true)
        categoryList = arrayListOf<CategoryClassOB>()
        categoryList.add(CategoryClassOB("Electronics", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Power Tools", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Kitchen tools & utensils", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Baby care & toys", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Cosmetics", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Lights", R.drawable.baseline_home))
        categoryList.add(CategoryClassOB("Others", R.drawable.baseline_home))

        val addAdaptor = UserCategoryAdaptor(categoryList)
        binding.recyclerView.adapter = addAdaptor
        addAdaptor.setOnCategoryClickListener(
            object : UserCategoryAdaptor.onCategoryClickListener {
                override fun onItemClick(position: Int) {
                    communicator.passData(
                        categoryList[position].category_Name,
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