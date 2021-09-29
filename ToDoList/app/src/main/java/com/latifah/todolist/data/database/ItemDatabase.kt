package com.latifah.todolist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latifah.todolist.data.models.Item
import com.latifah.todolist.logic.dao.ItemDao

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract val itemDao : ItemDao //this is how the database knows about the dao

    /*The companion object allows clients to access the methods
    for creating or getting the database without instantiating the class*/
    companion object {
        /*INSTANCE keeps a reference to the database, when one has been created*/
        //Volatile variable is never cached, this helps make sure the value of INSTANCE is always up-to-date and the same
        //to all execution threads. changes made by one thread to INSTANCE are visible to all other threads immediately
        @Volatile
        //Singleton- restricts the instantiation of a class to one "single" instance
        private var INSTANCE: ItemDatabase? = null //this is a singleton. It prevents multiple instances of the database opening at the same time.


        fun getInstance(context: Context) : ItemDatabase { //this function returns the singleton.

            // synchronized- only one thread of execution at a time can enter this block of code,
            // which makes sure the database only gets initialized once.
            synchronized(this) { //"this" allows us to get access to the context
                var instance = INSTANCE
                if (instance == null) { // if there is no database
                    instance = Room.databaseBuilder( //build one
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "item_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}