package com.example.mail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mail.model.Letter
import com.example.mail.model.Mail
import com.example.mail.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var  users: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)

        val data = sharedPreferences.getString("data", "")
        users = if (data!!.isEmpty()) {
            loadUsers()
        } else {
            Gson().fromJson(data, object : TypeToken<List<User>>() {}.type)
        }

        val user = sharedPreferences.getString("user", "")
        if(user!!.isNotEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setContentView(R.layout.activity_login)
    }

    override fun onClick(v: View?) {
        val username = username.text.toString()
        val password = password.text.toString()

        if(username.isNotEmpty() && password.isNotEmpty()) {
            val user = users.find { user -> user.username == username && user.password == password }
            if (user != null) {
                val editor = sharedPreferences.edit()
                val userJson = Gson().toJson(user)
                editor.putString("user", userJson)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loadUsers(): ArrayList<User> {
        val editor = sharedPreferences.edit()
        val users = ArrayList<User>()
        for(i in 1..3) {
            val mail = Mail("M$i", "google$i@mail.com")
            for( j in 1..9) {
                val letter = Letter(
                    "L$i$j", "Subject$i$j", "We've made your phone more helpful than ever. " +
                            "With Google Assistant shortcuts that get you to your favorite apps. And tools that " +
                            "simplify how you connect with loved ones. Discover the latest that Android has to " +
                            "offer—no OS upgrade needed.")
                mail.mail.add(letter)
            }
            val user = User("U$i", "user_$i", "password", mail)
            users.add(user)
        }
        val usersJson = Gson().toJson(users)
        editor.putString("data", usersJson)
        editor.apply()
        return users
    }
}