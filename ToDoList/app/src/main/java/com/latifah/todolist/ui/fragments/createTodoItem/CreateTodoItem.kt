package com.latifah.todolist.ui.fragments.createTodoItem

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.latifah.todolist.R
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.ui.viewmodels.ItemViewModel
import com.latifah.todolist.databinding.FragmentCreateTodoItemBinding
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CreateTodoItem : Fragment(R.layout.fragment_create_todo_item), DatePickerDialog.OnDateSetListener
 {

    private var _binding: FragmentCreateTodoItemBinding? = null //this is nullable because you can't inflate the layout until onCreateView() is called
    private val binding get() = _binding!!
    private var title = ""
    private var description = ""
    private var createdAt = ""
    private var isCompleted = ""
    private var dueDate = ""
    private val itemViewModel: ItemViewModel by viewModels()
     val myCal = Calendar.getInstance()

     private var calMonth = 0
     private var calDay = 0
     private var calYear = 0

    //TODO: this is super buggy. Find out the difference between onCreateView and onViewCreated. App crashes if the view isn't created yet, but can't figure out view binding when onViewCreated is used

    //OnCreateView - you inflate the layout. The fragment enters the CREATED state
    //onViewCreated - called after the view is created. In this method you would bind specific views to properties by calling findViewById()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTodoItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.tvDateSelected.text = "$calMonth/$calDay/$calYear"

//        binding.btnPickDate.setOnClickListener {
//            DatePickerDialog(requireContext(), datePicker,
//                myCal.get(Calendar.YEAR), myCal.get(Calendar.MONTH), myCal.get(Calendar.DAY_OF_MONTH)).show()
//
//        }
//        binding.tvDateSelected.text = "$calMonth/$calDay/$calYear"

        binding.btnConfirm.setOnClickListener {
            addItemToDB()
        }
    }

     val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
         myCal.set(Calendar.MONTH, month)
         myCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
         myCal.set(Calendar.YEAR, year)

         calMonth = month
         calDay = dayOfMonth
         calYear = year
         Log.d("month and day", "month and day : $month $dayOfMonth")
     }

     private  fun currentTime() {
         val simpleDateFmt = SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US)
         Log.d("Create Todo Item", "onViewCreated: $simpleDateFmt")
        createdAt = simpleDateFmt.format(myCal.time)

     }
    private fun addItemToDB() {

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
        val currentDate = sdf.format(Date())

         title = binding.etTodoTitle.text.toString()
         description = binding.etTodoDescription.text.toString()
        createdAt = currentDate
        if (!(title.isEmpty() || description.isEmpty())) {
            val item = Item(0, title, description, createdAt, false, "Sept 22")
            //add the item if all the fields are filled in
            itemViewModel.addItem(item)
            Toast.makeText(context, "Item created successfully!", Toast.LENGTH_SHORT).show()
            Log.i("create frag", "$title $description")

            //navigate back to the home screen
            findNavController().navigate(R.id.action_createTodoItem_to_todoList)
        } else {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        }
    }





     override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
         TODO("Not yet implemented")
     }


 }