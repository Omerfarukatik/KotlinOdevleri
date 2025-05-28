package com.example.foodapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.model.Food
import com.example.foodapp.databinding.ItemFoodBinding

class MainAdapter(
    private val onItemClick: (Food) -> Unit
) : ListAdapter<Food, MainAdapter.FoodViewHolder>(DiffCallback()) {

    // ViewHolder sınıfı: Her bir item için görsel bileşenleri bağlar
    inner class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            // Yemek adını ve fiyatını göster
            binding.foodName.text = food.yemek_adi
            binding.foodPrice.text = "${food.yemek_fiyat}₺"

            // Glide ile resim yükle
            Glide.with(binding.root.context)
                .load("http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}")
                .into(binding.foodImage)

            // Tıklama olayını dışarıdan gelen fonksiyona ilet
            binding.root.setOnClickListener {
                onItemClick(food)
            }
        }
    }

    // ViewHolder oluşturulurken çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    // ViewHolder'a verileri bağlar
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Liste farklarını optimize şekilde işlemek için kullanılır
    class DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.yemek_id == newItem.yemek_id
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }
}
