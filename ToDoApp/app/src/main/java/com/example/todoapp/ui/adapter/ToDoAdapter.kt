package com.example.todoapp.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.databinding.ItemTodoBinding

class ToDoAdapter(
    private val toDoList: List<ToDo>,
    private val onItemClick: (ToDo) -> Unit,
    private val onItemCheckToggle: (ToDo) -> Unit,
    private val onItemLongClick: (ToDo) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = toDoList[position]

        // Tarihi göster
        holder.binding.textViewDate.text = item.date

        // Başlığı göster ve duruma göre üzerini çiz
        holder.binding.textViewTitle.apply {
            text = item.title
            paintFlags = if (item.isDone) {
                paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        // ✏️ Kaleme tıklanınca düzenleme
        holder.binding.imageViewEdit.setOnClickListener {
            onItemClick(item)
        }

        // Kartın tamamına tıklayınca yapılma durumu değiştir
        holder.binding.root.setOnClickListener {
            val updated = item.copy(isDone = !item.isDone)
            onItemCheckToggle(updated)
        }

        // Uzun basınca sil
        holder.binding.root.setOnLongClickListener {
            onItemLongClick(item)
            true
        }
    }

    override fun getItemCount(): Int = toDoList.size
}
