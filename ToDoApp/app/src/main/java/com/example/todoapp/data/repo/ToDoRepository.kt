package com.example.todoapp.data.repo

import com.example.todoapp.data.dao.ToDoDao
import com.example.todoapp.data.entity.ToDo
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val dao: ToDoDao) {

    fun getAllToDos() = dao.getAllToDos()

    fun searchToDos(query: String) = dao.searchToDos(query)

    suspend fun insertToDo(toDo: ToDo) = dao.insertToDo(toDo)

    suspend fun updateToDo(toDo: ToDo) = dao.updateToDo(toDo)

    suspend fun deleteToDo(toDo: ToDo) = dao.deleteToDo(toDo)
}
