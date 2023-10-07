package com.example.findshop.fragments

import Shop
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.findshop.*
import java.io.ByteArrayOutputStream

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var shopList: ArrayList<Shop>
    private lateinit var shopAdapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Read shop data from database
        val dbHelper = DBHelper(requireContext())
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "SHOPINFO", null, null, null, null, null, null
        )
        shopList = ArrayList()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("SHOPID"))
                val name = getString(getColumnIndexOrThrow("SHOPNAME"))
                val desc = getString(getColumnIndexOrThrow("SHOPDESC"))
                val loc = getString(getColumnIndexOrThrow("SHOPLOC"))
                val contact = getString(getColumnIndexOrThrow("SHOPCONTACT"))
                val rating = getFloat(getColumnIndexOrThrow("RATING"))
                val photoUrl = getString(getColumnIndexOrThrow("SHOPPHOTO"))

                shopList.add(Shop(id, name, desc, loc, contact, rating, photoUrl))
            }
        }
        cursor.close()
        db.close()

        shopAdapter = ShopAdapter(shopList)
        recyclerView.adapter = shopAdapter

        shopAdapter.onItemClick={
            val intent=Intent(requireContext(),DetailedActivity::class.java)
            intent.putExtra("Shop",it)
            startActivity(intent)
        }

        return view
    }
}
