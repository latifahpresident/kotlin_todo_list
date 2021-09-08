package com.latifah.todolist.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.latifah.todolist.BottomFragment
import com.latifah.todolist.Model.TodoItem
import com.latifah.todolist.R
import com.latifah.todolist.R.id.editItemBtn


class TodoAdapter(private val todoListItems: MutableList<TodoItem>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>()  {



    //tells the app which view to layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val todoItems = inflater.inflate(R.layout.item_layout, parent, false) // <------- View to be laid out
        // Return a new holder instance
        return ViewHolder(todoItems)
    }


    //Add an item to the to do list
    fun addTodo(todo: TodoItem) {
        todoListItems.add(todo)
        Log.i("to do item", todo.description)
        Log.i("todo list size", todoListItems.size.toString())
        notifyItemInserted(todoListItems.size - 1)
        notifyDataSetChanged()

    }

    fun updateTodo(position: Int, update: String, todoId: Int) {
        Log.i("to do item update", update)
        Log.i("to do item id", todoId.toString())

//        val todoItem = todoListItems
//        for(i in 0 until todoItem.size){
//            Log.i("to do item update", todoItem[i].description)
//
//            print(todoListItems[i].description)
//        }
//        notifyItemChanged(position)

        for (item in todoListItems) {
            Log.i("update loop", "in for loop")

            println("to do item description" +  "${item.description}")
            Log.i("to do item description", item.description)
            if (item.id == position) {
                item.description = update
                notifyItemInserted(todoListItems.size - 1)
            } else {
                Log.i("error in update adapter", "there was an error updating the todoList ")
            }
        }
    }


    fun deleteDoneTodos() {
        todoListItems.removeAll { todo ->

            todo.completed
        }
        notifyDataSetChanged()
    }

    //BindViewHolder will bind the data from the todoListItems to the views of the list
    //called when a new list item is available in our list (item_layout)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodoItem: TodoItem = todoListItems[position]  //returns the position of the item you are interacting with
        Log.i("curr to do pos", "$position")
        holder.createdAtTxt.text = currentTodoItem.createdAt
        val itemCheckBox = holder.todoItemCB//right now this is holding out checkbox view
        val editBtn = holder.editItem//right now this is holding out check button that each t/odo item has
        val cardView = holder.card//right now this is holding out card view. will implement a way to delete on swipe

        itemCheckBox.text = currentTodoItem.description //set current to to
        currentTodoItem.id = position //sets the item of th
        itemCheckBox.isChecked = currentTodoItem.completed //set isChecked of the checkbox

        //CHANGES AN ITEM BEING CHECKED OR UNCHECKED
        itemCheckBox.setOnCheckedChangeListener { _, _ ->
            currentTodoItem.completed = !currentTodoItem.completed
        }

        itemCheckBox.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Checked box: " + (position + 1), Toast.LENGTH_SHORT
            ).show()
        }

        //EDIT BUTTON OPENS THE BOTTOM SHEET THAT HOLDS THE EDIT TEXT
        //WHERE A USER CAN UPDATE A T/ODO ITEM
        editBtn.setOnClickListener {

            val bottomSheetFrag =
                BottomFragment(position, currentTodoItem.description, currentTodoItem.id)
            val appCompatActivity = it.context as AppCompatActivity
            bottomSheetFrag.show(appCompatActivity.supportFragmentManager, "TAG")
        }

        cardView.setOnClickListener {



         Log.i("card view id", "$position")
            Toast.makeText(
                holder.itemView.context,
                "Card Clicked: " + (position + 1), Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return todoListItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val todoItemCB: CheckBox = itemView.findViewById(R.id.todoItemCB) // this is the checkbox from the custom item_layout
        val createdAtTxt: TextView = itemView.findViewById(R.id.createdAtTxtView)
        val editItem: Button =itemView.findViewById(editItemBtn)
        val card: CardView = itemView.findViewById(R.id.cardView)
    }
}