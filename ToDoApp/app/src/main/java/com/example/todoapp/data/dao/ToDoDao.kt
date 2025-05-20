package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.entity.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Query("SELECT * FROM todos WHERE title LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    fun searchToDos(searchQuery: String): LiveData<List<ToDo>>

    @Insert
    suspend fun insertToDo(toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)
}
