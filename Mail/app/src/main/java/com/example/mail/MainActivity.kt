package com.example.mail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mail.fragment.MailListFragment
import com.example.mail.model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString("user", "")

        if(userJson!!.isNotEmpty()) {
            val user = Gson().fromJson(userJson, User::class.java)

            val fragment = MailListFragment()
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .add(R.id.main, fragment)
                .commit()
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onClick(v: View?) {
        supportFragmentManager.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_logout) {
            val editor = sharedPreferences.edit()
            editor.remove("user")
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}