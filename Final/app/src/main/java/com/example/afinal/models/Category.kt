package com.example.afinal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name", index = true) var name: String? = null
)