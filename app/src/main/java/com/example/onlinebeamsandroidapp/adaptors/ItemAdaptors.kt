package com.example.onlinebeamsandroidapp.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R

class ItemAdaptors(val itemList: ArrayList<ItemClass>, val fragment: Fragment) :
    RecyclerView.Adapter<ItemAdaptors.myViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    class myViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.itemNameTV)
        val itemPrice = itemView.findViewById<TextView>(R.id.itemPriceTV)
        val itemImage = itemView.findViewById<ImageView>(R.id.imgItem)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdaptors.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemcardview,
            parent, false
        )
        return myViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ItemAdaptors.myViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemName.text = currentItem.item_Name
        val rs="Rs."
        holder.itemPrice.text = rs+currentItem.item_Price
        Glide.with(fragment)
            .load(currentItem.item_Image)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}