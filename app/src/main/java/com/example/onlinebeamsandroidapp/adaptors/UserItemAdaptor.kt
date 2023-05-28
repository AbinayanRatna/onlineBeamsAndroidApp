package com.example.onlinebeamsandroidapp.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R

class UserItemAdaptor(private var itemList: ArrayList<ItemClass>, val fragment: Fragment) :
    RecyclerView.Adapter<UserItemAdaptor.myViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    class myViewHolder(val itemView: View, clickListener: onItemClickListener) :
        ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.itemNameTV)
        val itemPrice = itemView.findViewById<TextView>(R.id.itemPriceTV)
        val itemImage = itemView.findViewById<ImageView>(R.id.imgItem)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserItemAdaptor.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemcardview,
            parent, false
        )
        return myViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: UserItemAdaptor.myViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemName.text = currentItem.item_Name
        val rs="Rs."
        holder.itemPrice.text = rs+currentItem.item_Price
        Glide.with(fragment)
            .load(itemList[position].item_Image)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}