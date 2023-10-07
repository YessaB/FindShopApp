package com.example.findshop

import Shop
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.findshop.fragments.MapsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailedActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var shop: Shop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        shop = intent.getParcelableExtra<Shop>("Shop")!!

        val textView: TextView = findViewById(R.id.textViewdetailedheading)
        val imageView: ImageView = findViewById(R.id.imageView2)
        val descriptiveTextView: TextView = findViewById(R.id.descriptionTextView)
        val gmaps = findViewById<Button>(R.id.showInMapsButton)
        val cb = findViewById<Button>(R.id.callbutton)
        val backk = findViewById<View>(R.id.backscreen)
        setRandomBackgroundColor(backk)
        cb.setOnClickListener {
            val phoneNumber = shop.contact
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(dialIntent)
        }

        gmaps.setOnClickListener {
            showMapsFragment(shop.name, shop.location)
            println(shop.location)
        }

        cb.text = "Contact Shop"
        descriptiveTextView.text = shop.description
        textView.text = shop.name
        Glide.with(this)
            .load(shop.photoUrl)
            .into(imageView)
    }
    fun setRandomBackgroundColor(view: View) {
        val randomColor = Color.rgb(
            (0..255).random(),
            (0..255).random(),
            (0..255).random()
        )
        view.setBackgroundColor(randomColor)
    }

    private fun showMapsFragment(shopName: String, shopLocation: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.map_container, MapsFragment.newInstance(shop.name, shop.location))
            .addToBackStack(null)
            .commit()
    }
}