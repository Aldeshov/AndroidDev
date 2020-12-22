package com.example.afinal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.afinal.models.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Insert
    fun insert(vararg item: Category)
}