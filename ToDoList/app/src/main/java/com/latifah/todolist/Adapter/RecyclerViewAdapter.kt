package com.latifah.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.latifah.todolist.Model.TodoItem
import com.latifah.todolist.R

class RecyclerViewAdapter(private val todoListItems: MutableList<TodoItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
//        val itemContent: TextView = itemView.findViewById<TextView>(R.id.item_content)
        val itemCompleted: CheckBox = itemView.findViewById<CheckBox>(R.id.isCompleted)


    }

    //tells the app which view to layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val todoItems = inflater.inflate(R.layout.item_layout, parent, false) // <------- View to be laid out
        // Return a new holder instance
        return ViewHolder(todoItems)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem: TodoItem = todoListItems[position]
//        val itemContentTextView = holder.itemContent
        val itemCheckBoxCompleted = holder.itemCompleted
//        itemContentTextView.text = todoItem.description
        itemCheckBoxCompleted.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Checked box: " + (position + 1), Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return todoListItems.size
    }
}