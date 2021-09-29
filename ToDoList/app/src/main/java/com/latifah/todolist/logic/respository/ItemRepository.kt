package com.latifah.todolist.logic.respository

import androidx.lifecycle.LiveData
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.logic.dao.ItemDao

//repository- primarily used to manage multiple data sources.
class ItemRepository (private val itemDao: ItemDao) {
    val getAllItems: LiveData<List<Item>> = itemDao.getAllItems()

    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    suspend fun deleteAllItems() {
        itemDao.deleteAll()
    }
}