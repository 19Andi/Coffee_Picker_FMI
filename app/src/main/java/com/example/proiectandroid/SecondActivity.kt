package com.example.proiectandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.proiectandroid.database.DatabaseSingleton
import com.example.proiectandroid.models.Coffee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CoffeesFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

        val sharedPreferences = getSharedPreferences("com.example.proiectandroid", Context.MODE_PRIVATE)
        val isDataInserted = sharedPreferences.getBoolean("isDataInserted", false)

        if (!isDataInserted) {
            insertInitialData()
            sharedPreferences.edit().putBoolean("isDataInserted", true).apply()
        }

        val logoutButton: ImageButton = findViewById(R.id.btn_logout)
        logoutButton.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun insertInitialData() {
        val initialCoffees = listOf(
            Coffee(name = "Espresso", type = "Hot", description = "A strong and concentrated coffee, served in small quantities."),
            Coffee(name = "Cappuccino", type = "Hot", description = "A combination of espresso, steamed milk, and a thick layer of milk foam."),
            Coffee(name = "Latte", type = "Hot", description = "Espresso with lots of steamed milk and a thin layer of milk foam."),
            Coffee(name = "Americano", type = "Hot", description = "Espresso diluted with hot water for a milder taste."),
            Coffee(name = "Mocha", type = "Hot", description = "Espresso mixed with hot chocolate and milk, often topped with whipped cream."),
            Coffee(name = "Flat White", type = "Hot", description = "Espresso with a thin layer of steamed milk, similar to a latte but with less milk."),
            Coffee(name = "Macchiato", type = "Hot", description = "Espresso with a small amount of steamed milk, served in a demitasse cup."),
            Coffee(name = "Affogato", type = "Hot", description = "Espresso poured over a scoop of vanilla ice cream."),
            Coffee(name = "Ristretto", type = "Hot", description = "A more concentrated version of espresso with a richer flavor."),
            Coffee(name = "Cortado", type = "Hot", description = "Espresso cut with a small amount of warm milk to reduce acidity."),
            Coffee(name = "Irish Coffee", type = "Hot", description = "Hot coffee mixed with Irish whiskey, sugar, and topped with cream."),
            Coffee(name = "Turkish Coffee", type = "Hot", description = "Very finely ground coffee beans simmered in water with sugar."),
            Coffee(name = "Vienna Coffee", type = "Hot", description = "Espresso mixed with whipped cream, often dusted with chocolate or cinnamon."),
            Coffee(name = "Red Eye", type = "Hot", description = "A cup of brewed coffee with a shot of espresso added."),
            Coffee(name = "Lungo", type = "Hot", description = "An espresso made with more water for a longer, milder extraction."),
            Coffee(name = "Iced Coffee", type = "Cold", description = "Hot brewed coffee cooled down and served over ice."),
            Coffee(name = "Cold Brew", type = "Cold", description = "Coffee made by steeping coarsely ground beans in cold water for an extended period."),
            Coffee(name = "Iced Latte", type = "Cold", description = "Espresso with cold milk and ice."),
            Coffee(name = "Frappuccino", type = "Cold", description = "Blended iced coffee with milk and various flavors."),
            Coffee(name = "Iced Mocha", type = "Cold", description = "Espresso with cold chocolate, milk, and ice."),
            Coffee(name = "Nitro Cold Brew", type = "Cold", description = "Cold brew coffee infused with nitrogen for a creamy texture."),
            Coffee(name = "Iced Americano", type = "Cold", description = "Espresso with cold water and ice."),
            Coffee(name = "Iced Macchiato", type = "Cold", description = "Espresso poured over cold milk and ice."),
            Coffee(name = "Coffee Smoothie", type = "Cold", description = "Blended coffee with milk, ice, and often fruit or other flavors."),
            Coffee(name = "Iced Flat White", type = "Cold", description = "Espresso with cold milk, similar to an iced latte but with less milk."),
            Coffee(name = "Coffee Milkshake", type = "Cold", description = "Blended coffee with ice cream and milk."),
            Coffee(name = "Shakerato", type = "Cold", description = "Espresso shaken with ice and sugar, served frothy."),
            Coffee(name = "Coffee Frappe", type = "Cold", description = "Blended iced coffee drink popular in Greece."),
            Coffee(name = "Iced Espresso", type = "Cold", description = "Straight espresso served over ice."),
            Coffee(name = "Iced Chai Latte", type = "Cold", description = "Chai tea mixed with cold milk and ice."),
            Coffee(name = "Espresso To Go", type = "ToGo", description = "A strong espresso served in a to-go cup."),
            Coffee(name = "Latte To Go", type = "ToGo", description = "A classic latte served in a to-go cup."),
            Coffee(name = "Cappuccino To Go", type = "ToGo", description = "A traditional cappuccino served in a to-go cup."),
            Coffee(name = "Iced Coffee To Go", type = "ToGo", description = "Cold coffee served in a to-go cup."),
            Coffee(name = "Cold Brew To Go", type = "ToGo", description = "Cold brew coffee served in a to-go cup."),
            Coffee(name = "Mocha To Go", type = "ToGo", description = "Mocha coffee served in a to-go cup."),
            Coffee(name = "Flat White To Go", type = "ToGo", description = "Flat white coffee served in a to-go cup."),
            Coffee(name = "Americano To Go", type = "ToGo", description = "Americano coffee served in a to-go cup."),
            Coffee(name = "Iced Latte To Go", type = "ToGo", description = "Iced latte served in a to-go cup."),
            Coffee(name = "Iced Mocha To Go", type = "ToGo", description = "Iced mocha served in a to-go cup."),
            Coffee(name = "Nitro Cold Brew To Go", type = "ToGo", description = "Nitro cold brew served in a to-go cup."),
            Coffee(name = "Iced Americano To Go", type = "ToGo", description = "Iced Americano served in a to-go cup."),
            Coffee(name = "Chai Latte To Go", type = "ToGo", description = "Chai latte served in a to-go cup."),
            Coffee(name = "Coffee Frappe To Go", type = "ToGo", description = "Frappe coffee served in a to-go cup."),
            Coffee(name = "Turkish Coffee To Go", type = "ToGo", description = "Traditional Turkish coffee served in a to-go cup.")
        )

        val db = DatabaseSingleton.getDatabase(this)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.coffeeDao().insertAllCoffees(initialCoffees)
            }
        }
    }
}
