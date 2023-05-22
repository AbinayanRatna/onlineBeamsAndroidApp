package com.example.onlinebeamsandroidapp.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinebeamsandroidapp.CategoryClassOB
import com.example.onlinebeamsandroidapp.R

class UserCategoryAdaptor(private val categoryList: ArrayList<CategoryClassOB>) :
    RecyclerView.Adapter<UserCategoryAdaptor.myViewHolder>() {
    private lateinit var mListener: onCategoryClickListener

    interface onCategoryClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnCategoryClickListener(clickListener: onCategoryClickListener) {
        mListener = clickListener
    }

    class myViewHolder(itemView: View, clickListener: onCategoryClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val categoryImage = itemView.findViewById<ImageView>(R.id.categoryImg)
        val categoryName = itemView.findViewById<TextView>(R.id.categoryNameTV)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserCategoryAdaptor.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.categorycardview, parent, false
        )
        return myViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: UserCategoryAdaptor.myViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.categoryImage.setImageResource(currentItem.category_Image!!)
        holder.categoryName.text = currentItem.category_Name
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}