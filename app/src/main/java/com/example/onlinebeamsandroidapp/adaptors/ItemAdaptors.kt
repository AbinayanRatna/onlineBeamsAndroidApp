package com.example.onlinebeamsandroidapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.ItemClass
import com.example.onlinebeamsandroidapp.R

class ItemAdaptors(val itemList:ArrayList<ItemClass>,private val context: Context):RecyclerView.Adapter<ItemAdaptors.myViewHolder>() {
    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.itemNameTV)
        val itemPrice=itemView.findViewById<TextView>(R.id.itemPriceTV)
        val itemImage=itemView.findViewById<ImageView>(R.id.imgItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdaptors.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemcardview,
            parent, false
        )
        return ItemAdaptors.myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdaptors.myViewHolder, position: Int) {
        val currentItem=itemList[position]
        holder.itemName.text=currentItem.item_Name
        holder.itemPrice.text=currentItem.item_Price
        Glide.with(context)
            .load(itemList[position].item_Image)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}