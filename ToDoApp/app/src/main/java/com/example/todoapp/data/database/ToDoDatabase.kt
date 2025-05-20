package com.example.todoapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.dao.ToDoDao
import com.example.todoapp.data.entity.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
