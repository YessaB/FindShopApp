package com.example.findshop

import Shop
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ShopAdapter(private val shopList: ArrayList<Shop>)
    : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    var onItemClick :((Shop) -> Unit)? =null



    class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.tview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return ShopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = shopList[position]
        Glide.with(holder.itemView.context)
            .load(shop.photoUrl)
            .into(holder.imageView)
        holder.textView.text = shop.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(shop)

        }



    }
}