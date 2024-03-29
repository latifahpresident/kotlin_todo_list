package com.latifah.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.latifah.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment)) //changes the title in the action bar automatically when the destination changes

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}















//    private lateinit var todoAdapter: TodoAdapter
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    val now: LocalDateTime = LocalDateTime.now()
//    @RequiresApi(Build.VERSION_CODES.O)
//    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
//    @RequiresApi(Build.VERSION_CODES.O)
//    val formatted: String = now.format(formatter)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        supportActionBar?.hide()
//
//        val  addItemBtn = findViewById<Button>(R.id.addTaskFab)
//        val  recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val  editItem = findViewById<Button>(R.id.editItemBtn)
//
//        val listOfTodoItems = mutableListOf<TodoItem>()
//
//        todoAdapter = TodoAdapter(listOfTodoItems)
//
//        val adapter = TodoAdapter(listOfTodoItems)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
////        addItemBtn.setOnClickListener {
////            val newTodoItem = findViewById<EditText>(R.id.newItemEditTxt).text
////            adapter.addTodo(TodoItem(0, newTodoItem.toString(), false, formatted))
//////            // Notify adapter
////            adapter.notifyDataSetChanged()
////            newTodoItem.clear()
////        }
//
//      Log.i("adapter size", "${listOfTodoItems.size}")
//
////
////            editItem.setOnClickListener {
////
////                var bottomFragment = BottomFragment()
//////
////                bottomFragment.show(supportFragmentManager, "TAG")
////            }
//
////        addItemFabBtn.setOnClickListener {
////            Log.i("new todo item", "stringForItem: $stringForItem ")
//////            adapter.addTodo(TodoItem(newTodoItemEditText, "false", "Dec 25"))
////            listOfTodoItems.add(TodoItem(stringForItem , false, formatted))
////            adapter.notifyDataSetChanged()
////
////        }
//
//
//
////        val todoItemFromBS = intent.getStringExtra("DATA")
////        Log.e("string from edit text", "edit text bottoms$todoItemFromBS")
//        //this is the on click listener to open the bottom sheet will implement once I figure it out
//
//
//
//
//
//
//
////        recyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
////
////        val listOfTodoItems = mutableListOf<TodoItem>() //Bring in the data model
////        // Create adapter passing in the sample user data
////
////        recyclerView?.adapter = adapter
////        // Set layout manager to position the items
////
//
//    }
