package com.latifah.todolist.ui.fragments.todoList.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.latifah.todolist.R
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.ui.fragments.todoList.TodoListDirections


class ItemListAdapter() : RecyclerView.Adapter<ItemListAdapter.ViewHolder>()  {

    val TAG = "ItemListAdapter"
    var itemList = emptyList<Item>()
        // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
            val cardView: CardView = itemView.findViewById(R.id.cv_cardView)
            val title: TextView = itemView.findViewById(R.id.tv_item_title)
            val description: TextView = itemView.findViewById(R.id.tv_item_description)
            val createdAt : TextView = itemView.findViewById(R.id.tv_item_createdTimeStamp)

        init { //set the click listener in an init block so the the items are immediately clickable when the adapter is called
            cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position")

                //pass current item to the update item fragment
                val action = TodoListDirections.actionTodoListToUpdateTodoItem(itemList[position])
                itemView.findNavController().navigate(action)
            }
       }

    }

//    //tells the app which view to layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        // Inflate the custom layout
//        val todoItems = inflater.inflate(R.layout.item_layout, parent, false) // <------- View to be laid out
        // Return a new holder instance
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)) //one liner of code above
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.createdAt.text = currentItem.createdAt

//        holder.cardView.setOnClickListener {
//            Log.d(TAG, "Item clicked at: $position")
//
//            //pass current item to the update item fragment
//            val action = TodoListDirections.actionTodoListToUpdateTodoItem(itemList[position])
//            holder.itemView.findNavController().navigate(action)
//        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(item: List<Item>) {
        this.itemList = item //update the itemList with the data from the observer
        notifyDataSetChanged()
    }
//
//
//    //Add an item to the to do list
//    fun addTodo(todo: TodoItem) {
//        todoListItems.add(todo)
//        Log.i("to do item", todo.description)
//        Log.i("todo list size", todoListItems.size.toString())
//        notifyItemInserted(todoListItems.size - 1)
//        notifyDataSetChanged()
//
//    }
//
//    fun updateTodo(position: Int, update: String, todoId: Int) {
//        Log.i("to do item update", update)
//        Log.i("to do item id", todoId.toString())
//
////        val todoItem = todoListItems
////        for(i in 0 until todoItem.size){
////            Log.i("to do item update", todoItem[i].description)
////
////            print(todoListItems[i].description)
////        }
////        notifyItemChanged(position)
//
//        for (item in todoListItems) {
//            Log.i("update loop", "in for loop")
//
//            println("to do item description" +  "${item.description}")
//            Log.i("to do item description", item.description)
//            if (item.id == position) {
//                item.description = update
//                notifyItemInserted(todoListItems.size - 1)
//            } else {
//                Log.i("error in update adapter", "there was an error updating the todoList ")
//            }
//        }
//    }
//
//
//    fun deleteDoneTodos() {
//        todoListItems.removeAll { todo ->
//
//            todo.completed
//        }
//        notifyDataSetChanged()
//    }
//
//    //BindViewHolder will bind the data from the todoListItems to the views of the list
//    //called when a new list item is available in our list (item_layout)
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentTodoItem: TodoItem = todoListItems[position]  //returns the position of the item you are interacting with
//        Log.i("curr to do pos", "$position")
//        holder.createdAtTxt.text = currentTodoItem.createdAt
//        val itemCheckBox = holder.todoItemCB//right now this is holding out checkbox view
//        val editBtn = holder.editItem//right now this is holding out check button that each t/odo item has
//        val cardView = holder.card//right now this is holding out card view. will implement a way to delete on swipe
//
//        itemCheckBox.text = currentTodoItem.description //set current to to
//        currentTodoItem.id = position //sets the item of th
//        itemCheckBox.isChecked = currentTodoItem.completed //set isChecked of the checkbox
//
//        if(position % 2 == 0 ) {
//            itemCheckBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#FFA600"))
//        }
//        //CHANGES AN ITEM BEING CHECKED OR UNCHECKED
//        itemCheckBox.setOnCheckedChangeListener { _, _ ->
//            currentTodoItem.completed = !currentTodoItem.completed
//        }
//
//        itemCheckBox.setOnClickListener {
//            Toast.makeText(
//                holder.itemView.context,
//                "Checked box: " + (position + 1), Toast.LENGTH_SHORT
//            ).show()
//        }
//
//        //EDIT BUTTON OPENS THE BOTTOM SHEET THAT HOLDS THE EDIT TEXT
//        //WHERE A USER CAN UPDATE A T/ODO ITEM
//        editBtn.setOnClickListener {
//
//            val bottomSheetFrag =
//                BottomFragment(position, currentTodoItem.description, currentTodoItem.id)
//            val appCompatActivity = it.context as AppCompatActivity
//            bottomSheetFrag.show(appCompatActivity.supportFragmentManager, "TAG")
//        }
//
//        cardView.setOnClickListener {
//
//
//
//         Log.i("card view id", "$position")
//            Toast.makeText(
//                holder.itemView.context,
//                "Card Clicked: " + (position + 1), Toast.LENGTH_SHORT
//            ).show()
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return todoListItems.size
//    }
//

}