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
    init { //initialize the repository object so we can get a list of all the items
        val itemDao = ItemDatabase.getInstance(application).itemDao
        repository = ItemRepository(itemDao)

        getAllItems = repository.getAllItems
    }

    /*
    * launch is a function that creates a coroutine and dispatches the execution of its function body to the corresponding dispatcher.
    * Dispatchers.IO indicates that this coroutine should be executed on a thread reserved for I/O operations.
    * Like in Redux where an action makes a call to the backend
*/
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