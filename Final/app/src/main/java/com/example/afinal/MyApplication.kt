package com.example.afinal

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {
    private var database: Database? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, Database::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): Database? {
        return database
    }

    companion object {
        lateinit var instance: MyApplication
    }
}