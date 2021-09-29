package com.latifah.todolist.ui.fragments.updateTodoItem

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.latifah.todolist.R
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.databinding.FragmentUpdateTodoItemBinding
import com.latifah.todolist.ui.viewmodels.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

class UpdateTodoItem : Fragment(R.layout.fragment_update_todo_item), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentUpdateTodoItemBinding? = null //this is nullable because you can't inflate the layout until onCreateView() is called
    private val binding get() = _binding!!
    private var title = ""
    private var description = ""
//    private var createdAt = ""
//    private var isCompleted = ""
//    private var dueDate = ""
    private val itemViewModel: ItemViewModel by viewModels()
    private val args by navArgs<UpdateTodoItemArgs>() // get the arguments that were passed from the adapter https://developer.android.com/guide/navigation/navigation-pass-data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateTodoItemBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //set the title and description of the edit text to the item we sent from the adapter
        binding.etTodoTitle.setText(args.selectedItem.title)
        binding.etTodoDescription.setText(args.selectedItem.description)

        binding.btnConfirmUpdate.setOnClickListener {
            updateItemToDB()
        }
    }

    private fun updateItemToDB() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
        val currentDate = sdf.format(Date())

        title = binding.etTodoTitle.text.toString()
        description = binding.etTodoDescription.text.toString()
        if (!(title.isEmpty() || description.isEmpty())) {
            val item = Item(args.selectedItem.id, title, description, currentDate, false, "Sept 22")
            //add the item if all the fields are filled in
            itemViewModel.updateItem(item)
            Toast.makeText(context, "Item updated successfully!", Toast.LENGTH_SHORT).show()
            Log.i("create frag", "$title $description")

            //navigate back to the home screen
            findNavController().navigate(R.id.action_updateTodoItem_to_todoList)
        } else {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> deleteItem(args.selectedItem)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem(item: Item) {
        itemViewModel.deleteItem(item)
        findNavController().navigate(R.id.action_updateTodoItem_to_todoList)
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        TODO("Not yet implemented")
    }
}