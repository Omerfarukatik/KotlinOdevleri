package com.example.foodapp.ui.cart

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.util.Constants
import com.example.foodapp.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        adapter = CartAdapter { cartItem ->
            viewModel.removeFromCart(cartItem.sepet_yemek_id, Constants.KULLANICI_ADI)
        }

        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fetchCartItems(Constants.KULLANICI_ADI)

        viewModel.cartItems.observe(viewLifecycleOwner) { cartList ->
            adapter.submitList(cartList)
            calculateTotal(cartList)
        }

        binding.confirmButton.setOnClickListener {
            Toast.makeText(requireContext(), "Siparişiniz alındı 🎉", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateTotal(cartList: List<com.example.foodapp.data.model.CartItem>) {
        val total = cartList.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
        binding.totalText.text = "Toplam: ₺$total"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
