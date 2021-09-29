package com.latifah.todolist.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.latifah.todolist.data.models.Item

//The Dao is how you access the database. It's where you make your queries. Similar to the models files in Nodejs
@Dao
interface ItemDao {

    //Below are CRUD functions to get access to the data

    //TODO: change this so that if an item with the same id already exist throw an error
    @Insert(onConflict = OnConflictStrategy.IGNORE)  //if there's an item with the same id ignore it
    suspend fun addItem(item: Item) //adds an item to the db

    @Update
    suspend fun updateItem(item : Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query ("SELECT * FROM todo_item ORDER BY id DESC")
    fun getAllItems(): LiveData<List<Item>>

    @Query ("DELETE FROM todo_item")
    suspend fun deleteAll()
}