package com.example.findshop.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.findshop.DBHelper
import com.example.findshop.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val editText1 = view.findViewById<EditText>(R.id.shopnamee)
        val editText2 = view.findViewById<EditText>(R.id.shopdec)
        val editText3 = view.findViewById<EditText>(R.id.shoplocc)
        val editText4 = view.findViewById<EditText>(R.id.shopcontactt)
        val editText5 = view.findViewById<EditText>(R.id.shopimagee)

        val button = view.findViewById<Button>(R.id.addshopbtn)

        button.setOnClickListener {
            val helper = DBHelper(requireContext())
            val db = helper.readableDatabase

            if (editText1.text.isEmpty()
                || editText2.text.isEmpty()
                || editText3.text.isEmpty()
                || editText4.text.isEmpty()
                || editText5.text.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all inputs!!", Toast.LENGTH_SHORT).show()
            } else {
                val cv = ContentValues()
                cv.put("SHOPNAME", editText1.text.toString())
                cv.put("SHOPDESC", editText2.text.toString())
                cv.put("SHOPLOC", editText3.text.toString())
                cv.put("SHOPCONTACT", editText4.text.toString())
                cv.put("SHOPPHOTO", editText5.text.toString())
                cv.put("RATING", 3)
                db.insert("SHOPINFO",null,cv)
                editText1.setText("")
                editText2.setText("")
                editText3.setText("")
                editText4.setText("")
                editText5.setText("")
                Toast.makeText(requireContext(), "Shop added Successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}