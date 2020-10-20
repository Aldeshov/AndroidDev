package com.example.midterm


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.midterm.model.ToDoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.todo_add_item.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var  items: ArrayList<ToDoItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)

        val data = sharedPreferences.getString("data", "")
        items = if (data!!.isEmpty()) {
            addItems()
        } else {
            Gson().fromJson(data, object : TypeToken<List<ToDoItem>>() {}.type)
        }

        setContentView(R.layout.activity_main)

        val fragment = ToDoListFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("data", items)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
                .add(R.id.main, fragment)
                .commit()
    }

    override fun onClick(v: View?) {
        supportFragmentManager.popBackStack()
    }

    fun addItem(view: View) {
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.main, ToDoAddFragment())
            .addToBackStack("secondary")
            .commit()
    }

    fun add(view: View) {
        val id = id.text.toString()
        val title = "See Description!"
        // Can not get title from todo_add_item layout!
        // Gives error: title not found
        val category = category.text.toString()
        val duration = duration.text.toString()
        val description = description.text.toString() + "\nDescription Works!" +
                "\n But Title not working! See the Code: MainActivity -> add() -> title"

        items.add(ToDoItem(id, title, description, false, category, duration))
        val editor = sharedPreferences.edit()
        val itemsJson = Gson().toJson(items)
        editor.putString("data", itemsJson)
        editor.apply()
        supportFragmentManager.popBackStack()
    }

    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    private fun addItems(): ArrayList<ToDoItem> {
        val editor = sharedPreferences.edit()
        val items = ArrayList<ToDoItem>()

        for(i in 1..10) {
            val c = rand(1,5)
            val d = rand(10, 20)
            items.add(
                    ToDoItem(
                            "ID$i",
                            "Title$i",
                            "\n" +
                                    "██████╗░░█████╗░██████╗░██╗░░░██╗\n" +
                                    "██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
                                    "██████╦╝██║░░██║██║░░██║░╚████╔╝░\n" +
                                    "██╔══██╗██║░░██║██║░░██║░░╚██╔╝░░\n" +
                                    "██████╦╝╚█████╔╝██████╔╝░░░██║░░░\n" +
                                    "╚═════╝░░╚════╝░╚═════╝░░░░╚═╝░░░",
                            false,
                            "Category$c",
                            "D: $d"
                    )
            )
        }
        val itemsJson = Gson().toJson(items)
        editor.putString("data", itemsJson)
        editor.apply()
        return items
    }
}