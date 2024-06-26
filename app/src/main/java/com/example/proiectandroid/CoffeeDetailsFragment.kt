package com.example.proiectandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CoffeeDetailsFragment : Fragment() {

    companion object {
        private const val ARG_COFFEE_NAME = "coffee_name"
        private const val ARG_COFFEE_DESCRIPTION = "coffee_description"

        fun newInstance(name: String, description: String): CoffeeDetailsFragment {
            val fragment = CoffeeDetailsFragment()
            val args = Bundle()
            args.putString(ARG_COFFEE_NAME, name)
            args.putString(ARG_COFFEE_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coffee_details, container, false)

        val coffeeName = arguments?.getString(ARG_COFFEE_NAME)
        val coffeeDescription = arguments?.getString(ARG_COFFEE_DESCRIPTION)

        val tvCoffeeName: TextView = view.findViewById(R.id.tvCoffeeName)
        val tvCoffeeDescription: TextView = view.findViewById(R.id.tvCoffeeDescription)
        val btnClose: Button = view.findViewById(R.id.btnClose)

        tvCoffeeName.text = coffeeName
        tvCoffeeDescription.text = coffeeDescription

        btnClose.setOnClickListener {
            Toast.makeText(context, "Connect to a \ncoffee machine first!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
