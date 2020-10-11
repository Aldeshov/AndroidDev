package com.example.todo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


var items = ArrayList<ToDoItem>()

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main, ToDoListFragment())
            .commit()
        addItems(items)
    }

    override fun onClick(v: View?) {
        supportFragmentManager.popBackStack()
    }

    private fun addItems(items: ArrayList<ToDoItem>) {
        for(i in 1..25) {
            items.add(
                ToDoItem(
                    "ID$i", "Header$i", "This is short body$i",
                            "\n" +
                                    "██████╗░░█████╗░██████╗░██╗░░░██╗\n" +
                                    "██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
                                    "██████╦╝██║░░██║██║░░██║░╚████╔╝░\n" +
                                    "██╔══██╗██║░░██║██║░░██║░░╚██╔╝░░\n" +
                                    "██████╦╝╚█████╔╝██████╔╝░░░██║░░░\n" +
                                    "╚═════╝░░╚════╝░╚═════╝░░░░╚═╝░░░",
                    false
                )
            )
        }
    }
}