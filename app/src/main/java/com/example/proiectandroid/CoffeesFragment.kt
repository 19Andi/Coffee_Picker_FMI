package com.example.proiectandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proiectandroid.adapters.CoffeesAdapter
import com.example.proiectandroid.database.AppDatabase
import com.example.proiectandroid.models.Coffee
import kotlinx.coroutines.launch

class CoffeesFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var coffeesAdapter: CoffeesAdapter
    private lateinit var coffeesRecyclerView: RecyclerView
    private lateinit var btnHotCoffees: Button
    private lateinit var btnColdCoffees: Button
    private lateinit var btnToGo: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coffees, container, false)

        db = AppDatabase.getDatabase(requireContext())

        coffeesRecyclerView = view.findViewById(R.id.coffeesRecyclerView)
        coffeesRecyclerView.layoutManager = LinearLayoutManager(context)

        coffeesAdapter = CoffeesAdapter(mutableListOf()) { coffee ->
            navigateToCoffeeDetails(coffee)
        }
        coffeesRecyclerView.adapter = coffeesAdapter

        val btnHotCoffees = view.findViewById<ImageButton>(R.id.btnHotCoffees)
        val btnColdCoffees = view.findViewById<ImageButton>(R.id.btnColdCoffees)
        val btnToGo = view.findViewById<ImageButton>(R.id.btnToGo)

        btnHotCoffees.setOnClickListener { fetchCoffeesByType("Hot") }
        btnColdCoffees.setOnClickListener { fetchCoffeesByType("Cold") }
        btnToGo.setOnClickListener { fetchCoffeesByType("ToGo") }

        fetchCoffeesByType("Hot")

        return view
    }

    private fun fetchCoffeesByType(type: String) {
        lifecycleScope.launch {
            val coffees = db.coffeeDao().getCoffeesByType(type)
            Log.d("CoffeesFragment", "Fetched coffees: $coffees")
            coffeesAdapter.updateData(coffees)
        }
    }

    private fun navigateToCoffeeDetails(coffee: Coffee) {
        val coffeeDetailsFragment = CoffeeDetailsFragment.newInstance(coffee.name, coffee.description)
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, coffeeDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
}