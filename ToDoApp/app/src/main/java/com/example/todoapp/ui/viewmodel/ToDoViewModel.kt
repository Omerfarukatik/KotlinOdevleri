package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val toDoList = repository.getAllToDos()

    fun searchToDos(query: String) = repository.searchToDos(query)

    fun insertToDo(toDo: ToDo) {
        viewModelScope.launch {
            repository.insertToDo(toDo)
        }
    }




    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            repository.deleteToDo(toDo)
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            repository.updateToDo(toDo)
        }
    }

}
