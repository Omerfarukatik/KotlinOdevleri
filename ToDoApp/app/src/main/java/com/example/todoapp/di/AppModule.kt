package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.dao.ToDoDao
import com.example.todoapp.data.repo.ToDoRepository
import com.example.todoapp.data.room.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(database: ToDoDatabase): ToDoDao = database.toDoDao()
}
