package com.latifah.todolist.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.latifah.todolist.data.database.ItemDatabase
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.logic.respository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//The ViewModel's role is to provide data to the UI and survive configuration changes
//the application param tells us the global application state
class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ItemRepository
    val getAllItems: LiveData<List<Item>>

    //the init block is always called after the primary constructor is instantiated
    init { //initialize the repository object
        val itemDao = ItemDatabase.getInstance(application).itemDao
        repository = ItemRepository(itemDao)

        getAllItems = repository.getAllItems
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }
}