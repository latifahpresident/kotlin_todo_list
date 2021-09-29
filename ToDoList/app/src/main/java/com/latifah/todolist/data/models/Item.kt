package com.latifah.todolist.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize //give the ability to pass this as an argument and ability to retrieve in other fragments
@Entity(tableName = "todo_item") //creating a table in the room db

data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val isCompleted: Boolean,
    val dueDate: String
) : Parcelable
