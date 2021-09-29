package com.latifah.todolist.ui.fragments.todoList

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latifah.todolist.R
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.databinding.FragmentTodoListBinding
import com.latifah.todolist.ui.viewmodels.ItemViewModel
import com.latifah.todolist.ui.fragments.todoList.adapter.ItemListAdapter

const val TAG = "TodoListFrag"

class TodoList : Fragment() {
    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemList : List<Item>
    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var adapter: ItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)
        binding.fabAddItem.setOnClickListener {
            Log.i(TAG, "fab btn was clicked")
            findNavController().navigate(R.id.action_todoList_to_createTodoItem)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvItems = view.findViewById<RecyclerView>(R.id.rv_items)

        adapter = ItemListAdapter()
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(context)

        itemViewModel.getAllItems.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setData(it)

                itemList = it
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    //TODO get menu to display
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        itemViewModel.deleteAllItems()
    }
    /**
     * Frees the binding object when the Fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}