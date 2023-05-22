package com.example.onlinebeamsandroidapp.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinebeamsandroidapp.CartClass
import com.example.onlinebeamsandroidapp.R

class CartAdaptorClass(private val cartItemList: ArrayList<CartClass>, val fragment: Fragment) :
    RecyclerView.Adapter<CartAdaptorClass.myViewHolder>() {
    class myViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.itemNameTV)
        val itemCount = itemView.findViewById<TextView>(R.id.itemCountTV)
        val itemImage = itemView.findViewById<ImageView>(R.id.imgItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdaptorClass.myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartcardview, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdaptorClass.myViewHolder, position: Int) {
        val currentItem = cartItemList[position]
        holder.itemName.text = currentItem.item_Name
        holder.itemCount.text = currentItem.item_Name.toString()
        Glide.with(fragment).load(currentItem.item_Image).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }
}