package com.latifah.todolist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.latifah.todolist.Adapter.TodoAdapter
import com.latifah.todolist.Model.TodoItem


class BottomFragment(var position: Int,  private val curDescription: String, private val todoId: Int) : BottomSheetDialogFragment() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.new_item, container, false)
        val saveBtn = view.findViewById<Button>(R.id.saveNewItemBtn)
        val listOfTodoItems = mutableListOf<TodoItem>()
        val newItemEd = view.findViewById<EditText>(R.id.EditItem)
        newItemEd.hint = curDescription
        todoAdapter = TodoAdapter(listOfTodoItems)

        val adapter = TodoAdapter(listOfTodoItems)

            saveBtn.setOnClickListener {

                val newItemEd = view.findViewById<EditText>(R.id.EditItem)
                adapter.updateTodo(position, newItemEd.text.toString(), todoId)
                adapter.notifyItemChanged(position)

                Toast.makeText(
                    it.context, "id $todoId description:" +  newItemEd.text.toString(),Toast.LENGTH_SHORT
            ).show()

//                newItemEd.text.clear()
        }



        return view

}
//    override fun onViewCreated(
//        view: View,
//        savedInstanceState: Bundle?
//    ) {
//
//        requireDialog().window?.setWindowAnimations(
//            R.style.DialogAnimation
//        )
//    }
}












//val saveBtn = view.findViewById<Button>(R.id.saveNewItemBtn)
//val addNewItem = view.findViewById<EditText>(R.id.newItemEditTxt)
//val  recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//
//val listOfTodoItems = mutableListOf<TodoItem>()
//
//val adapter =  TodoAdapter(listOfTodoItems)
//recyclerView.adapter = adapter
//recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//saveBtn.setOnClickListener {
//
//    adapter.addTodo(TodoItem(addNewItem.text.toString(), "false", "Dec 25"))
//    listOfTodoItems.add(TodoItem(addNewItem.text.toString(), "false", "Dec 25"))
//
//    adapter.notifyDataSetChanged()
//}
