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
import com.example.onlinebeamsandroidapp.isInternetAvailable


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
        categoryList.add(CategoryClassOB("Electronics", R.drawable.electronic))
        categoryList.add(CategoryClassOB("Power Tools", R.drawable.powertool))
        categoryList.add(CategoryClassOB("Kitchen tools & utensils", R.drawable.kitchen))
        categoryList.add(CategoryClassOB("Baby care & toys", R.drawable.babycare))
        categoryList.add(CategoryClassOB("Cosmetics", R.drawable.cosmetics))
        categoryList.add(CategoryClassOB("Lights", R.drawable.light))
        categoryList.add(CategoryClassOB("Others", R.drawable.other))

        val addAdaptor = UserCategoryAdaptor(categoryList)
        binding.recyclerView.adapter = addAdaptor
        addAdaptor.setOnCategoryClickListener(
            object : UserCategoryAdaptor.onCategoryClickListener {
                override fun onItemClick(position: Int) {
                    if(isInternetAvailable(requireContext())){
                        communicator.passData(
                            categoryList[position].category_Name,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            UserItemFragment()
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