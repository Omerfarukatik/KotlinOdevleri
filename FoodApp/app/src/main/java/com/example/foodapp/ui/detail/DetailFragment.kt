package com.example.foodapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.model.Food
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.viewmodel.CartViewModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedFood: Food
    private var quantity = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectedFood = arguments?.getSerializable("food") as Food

        binding.foodName.text = selectedFood.yemek_adi
        binding.foodPrice.text = "₺${selectedFood.yemek_fiyat}"
        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${selectedFood.yemek_resim_adi}")
            .into(binding.foodImage)

        binding.quantityText.text = quantity.toString()

        binding.plusButton.setOnClickListener {
            quantity++
            binding.quantityText.text = quantity.toString()
        }

        binding.minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.quantityText.text = quantity.toString()
            }
        }

        binding.addToCartButton.setOnClickListener {
            val viewModel = ViewModelProvider(this)[CartViewModel::class.java]
            viewModel.addToCart(selectedFood, quantity, "kasim_adalan") // kullanıcı adı sabit örnek
            Toast.makeText(requireContext(), "Sepete Eklendi!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
