package com.example.proiectandroid.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proiectandroid.models.Coffee

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffees WHERE type = :type")
    suspend fun getCoffeesByType(type: String): List<Coffee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCoffees(coffees: List<Coffee>)
}