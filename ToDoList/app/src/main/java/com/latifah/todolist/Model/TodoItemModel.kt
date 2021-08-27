package com.latifah.todolist.Model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
val now = LocalDateTime.now()
@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss.SSS")
@RequiresApi(Build.VERSION_CODES.O)
val formatted: String = now.format(formatter)

data class TodoItem (val description: String, val completed: Boolean, val createdAt: String = formatted)





