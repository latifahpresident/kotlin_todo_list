package com.latifah.todolist

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latifah.todolist.Adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private var recyclerView : RecyclerView? = null

//    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val listOfTodoItems = mutableListOf<TodoItem>() //Bring in the data model
        // Create adapter passing in the sample user data
        val adapter = RecyclerViewAdapter(listOfTodoItems)
        recyclerView?.adapter = adapter
        // Set layout manager to position the items
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val stringForItem = findViewById<EditText>(R.id.stringToInput).text.toString() //this is the user input from the edit text

    }
}