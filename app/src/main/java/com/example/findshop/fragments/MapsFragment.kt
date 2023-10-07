package com.example.findshop.fragments

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.findshop.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var shopName: String? = null
    private var shopLocation: String? = null

    companion object {
        fun newInstance(shopName: String, shopLocation: String): MapsFragment {
            val args = Bundle()
            args.putString("shopName", shopName)
            args.putString("shopLocation", shopLocation)
            val fragment = MapsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            shopName = it.getString("shopName")
            shopLocation = it.getString("shopLocation")

        }

        val gmapsButton = activity?.findViewById<View>(R.id.showInMapsButton)
        gmapsButton?.visibility = View.GONE

        val cb = activity?.findViewById<View>(R.id.callbutton)
        cb?.visibility = View.GONE


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        println(shopLocation)
        shopLocation?.let {
            try {
                val addresses = geocoder.getFromLocationName(it, 1)
                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val latLng = addresses[0]?.let { it1 ->
                            LatLng(
                                it1.latitude,
                                addresses[0].longitude
                            )
                        }
                        googleMap.addMarker(latLng?.let { it1 ->
                            MarkerOptions().position(it1).title(shopName)
                        })
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    } else {
                        Toast.makeText(requireContext(), "Location not found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: IOException) {
                Toast.makeText(requireContext(), "Error finding location", Toast.LENGTH_SHORT)
                    .show()


            }
        }
    }
}
