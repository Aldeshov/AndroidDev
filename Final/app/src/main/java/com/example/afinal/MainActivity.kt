package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.example.afinal.dao.CategoryDao
import com.example.afinal.dao.CountryDao

class MainActivity : AppCompatActivity() {
    lateinit var db: Database
    lateinit var countryDao: CountryDao
    lateinit var categoryDao: CategoryDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MyApplication.instance.getDatabase()!!
        countryDao = db.countryDao()
        categoryDao = db.categoryDao()

        if(countryDao.getAll().isEmpty()) {
            addItems(1)
            Log.e("INITIAL Country", "Ok: Added")
        }

        if(categoryDao.getAll().isEmpty()) {
            addItems(2)
            Log.e("INITIAL Category", "Ok: Added")
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.setGraph(R.navigation.nav_graph)
        navHostFragment.navController.navigateUp()
    }

    private fun addItems(code: Int) {
        if(code == 1) {
            val item = com.example.afinal.models.Country()
            item.name = "India"
            item.code = "in"
            countryDao.insert(item)
            item.name = "USA"
            item.code = "us"
            countryDao.insert(item)
            item.name = "Australia"
            item.code = "au"
            countryDao.insert(item)
            item.name = "Russia"
            item.code = "ru"
            countryDao.insert(item)
            item.name = "France"
            item.code = "fr"
            countryDao.insert(item)
            item.name = "United Kingdom"
            item.code = "gb"
            countryDao.insert(item)
        }

        if(code == 2) {
            val item = com.example.afinal.models.Category()
            item.name = "business"
            categoryDao.insert(item)
            item.name = "entertainment"
            categoryDao.insert(item)
            item.name = "general"
            categoryDao.insert(item)
            item.name = "health"
            categoryDao.insert(item)
            item.name = "science"
            categoryDao.insert(item)
            item.name = "sports"
            categoryDao.insert(item)
            item.name = "technology"
            categoryDao.insert(item)
        }
    }
}