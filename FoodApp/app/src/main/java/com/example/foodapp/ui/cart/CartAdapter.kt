package com.example.foodapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.model.CartItem
import com.example.foodapp.databinding.ItemCartBinding

class CartAdapter(
    private val onDeleteClick: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.foodName.text = item.yemek_adi
            binding.foodQuantity.text = "Adet: ${item.yemek_siparis_adet}"
            binding.foodPrice.text = "₺${item.yemek_fiyat}"

            Glide.with(binding.root.context)
                .load("http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}")
                .into(binding.foodImage)

            binding.deleteButton.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem.sepet_yemek_id == newItem.sepet_yemek_id

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem == newItem
    }
}
